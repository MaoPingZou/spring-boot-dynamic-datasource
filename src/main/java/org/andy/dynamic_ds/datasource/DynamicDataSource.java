package org.andy.dynamic_ds.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MaoPing Zou
 * @date 2022/5/21 16:19
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(LoadDataSource loadDataSource) {
        // 1.设置所有的数据源
        Map<String, DataSource> allDs = loadDataSource.loadAllDataSource();
        super.setTargetDataSources(new HashMap<>(allDs));
        // 2.设置默认的数据源
        // 因为将来并不是会在所有的方法上都用 @DataSource 方法，所以对于那些没有 @DataSource注解的方法，需要设置一个默认的数据源
        super.setDefaultTargetDataSource(allDs.get(DataSourceType.DEFAULT_MASTER_DS));
        // 调用父类的方法完成 属性set后 配置
        super.afterPropertiesSet();
    }

    /**
     * 这个方法用来返回数据源名称，当系统需要获取数据源的时候，会自动调用该方法获取数据源的名称
     * 这个方法是重写了父类的方法，从当前线程中获取使用的数据源类型；
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
