package org.andy.dynamic_ds.datasource;

import org.springframework.stereotype.Component;

/**
 * 这个工具类用于存放当前 线程 所使用的 数据源名称
 * @author MaoPing Zou
 * @date 2022/5/21 15:23
 */
@Component
public class DynamicDataSourceContextHolder {

    /**
     * 在service层存的东西要在 mapper中拿到，
     * 因为 这两个在请求的时候是在同一个线程中，所以这里很自然的就会想到使用 ThreadLocal 解决问题
     */
    public static ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(String dataType) {
        CONTEXT_HOLDER.set(dataType);
    }

    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * ThreadLocal 里面的数据 用完之后一定要记得清空掉，
     * 不然那个线程将来去做其他事情就有可能产生 内存泄漏，因为没有人去管理它了
     */
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }

}
