package org.andy.dynamic_ds.aspect;

import org.andy.dynamic_ds.datasource.DataSourceType;
import org.andy.dynamic_ds.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * 用于全局设置数据源
 *
 * @author MaoPing Zou
 * @date 2022/5/29 10:00
 */
@Aspect
@Component
@Order(10)
public class GlobalDataSourceAspect {

    @Autowired
    private HttpSession session;

    /**
     * service 类里面的任意方法都会
     */
    @Pointcut("execution(* org.andy.dynamic_ds.service.*.*(..))")
    public void pc() {

    }

    @Around("pc()")
    public Object around(ProceedingJoinPoint pjp) {
        // 从session中通过key值取出保存好的数据源类型
        // 将取到的数据源类型放到 动态数据源上下文持有器 里面备用。
        DynamicDataSourceContextHolder.setDataSourceType(((String) session.getAttribute(DataSourceType.DS_SESSION_KEY)));
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            // ThreadLocal 变量用完一定要清楚掉
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
        return null;
    }


}
