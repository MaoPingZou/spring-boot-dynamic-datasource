package org.andy.dynamic_ds.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author MaoPing Zou
 * @date 2022/5/21 16:07
 */
@Component
@EnableConfigurationProperties
public class LoadDataSource {

    @Autowired
    DruidProperties druidProperties;

    /**
     * 用于加载所有的数据源
     */
    public Map<String, DataSource> loadAllDataSource() {
        Map<String, DataSource> map = new HashMap<>();
        Map<String, Map<String, String>> ds = druidProperties.getDs();
        try {
            Set<String> keySet = ds.keySet();
            for (String key : keySet) {
                // 使用 DruidDataSourceFactory 工厂根据给定的 参数map创建一个数据源，此时数据源对象中只有 url、username、password三个值
                // 然后使用 druidProperties中自定义的dataSource 方法将 其他数据源的属性 设置好
                map.put(key, druidProperties.dataSource((DruidDataSource) DruidDataSourceFactory.createDataSource(ds.get(key))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
