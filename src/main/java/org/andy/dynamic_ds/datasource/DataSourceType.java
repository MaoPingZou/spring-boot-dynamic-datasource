package org.andy.dynamic_ds.datasource;

/**
 * @author MaoPing Zou
 * @date 2022/5/21 16:23
 */
public interface DataSourceType {
    /**
     * 主数据库
     */
    String DEFAULT_MASTER_DS = "master";
    /**
     * 从数据库
     */
    String SLAVE_DS = "slave";

    String DS_SESSION_KEY = "ds_session_key";
}
