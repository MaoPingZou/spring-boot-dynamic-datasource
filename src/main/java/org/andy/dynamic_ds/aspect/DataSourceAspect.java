package org.andy.dynamic_ds.aspect;

import org.andy.dynamic_ds.annotation.DataSource;
import org.andy.dynamic_ds.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author MaoPing Zou
 * @date 2022/5/21 15:36
 */
@Aspect
@Component
@Order(11)
public class DataSourceAspect {

    /**
     * @annotation(org.andy.dynamic_ds.annotation.DataSource) 表示service层方法上又 @DataSource 注解就将该方法拦截下来
     * @within(org.andy.dynamic_ds.annotation.DataSource 表示service层类上面又 @DataSource 注解，就将类中的方法拦截下来
     */
    @Pointcut("@annotation(org.andy.dynamic_ds.annotation.DataSource) " +
            "|| @within(org.andy.dynamic_ds.annotation.DataSource)")
    public void pc() {

    }

    @Around("pc()")
    public Object around(ProceedingJoinPoint pjp) {
        // 获取方法上面的有效注解
        DataSource dataSource = getDataSource(pjp);
        if (dataSource != null) {
            // 获取注解中数据源的名称
            String value = dataSource.value();
            // set的 ThreadLocal 中
            DynamicDataSourceContextHolder.setDataSourceType(value);
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            // 一定要记得在finally中 清除 ThreadLocal 中使用过的数据变量
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
        return null;
    }

    /**
     * 获取数据源注解@DataSoruce
     * 先在方法上面找，找到直接返回，找不到再从类上面的找。
     */
    private DataSource getDataSource(ProceedingJoinPoint pip) {
        MethodSignature signature = (MethodSignature) pip.getSignature();
        // 查找方法上的注解
        DataSource annotation = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
        if (annotation != null) {
            // 说明方法上面有 @DataSource 注解
            return annotation;
        }
        // 否则去类上面找 是否存在 @DataSource 注解
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
    }

}
