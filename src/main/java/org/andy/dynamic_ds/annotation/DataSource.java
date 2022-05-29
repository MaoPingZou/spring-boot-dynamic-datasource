package org.andy.dynamic_ds.annotation;

import org.andy.dynamic_ds.datasource.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这个注解将来可以加在某一个 service 层的类上或方法上，通过 value 属性来指定类或者方法应该使用哪个数据源
 * @author MaoPing Zou
 * @date 2022/5/21 15:20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataSource {
    /**
     * 如果方法上设置了 @DataSource 注解，但是却没有指定数据源名称，那么就默认使用 master 数据源
     */
    String value() default DataSourceType.DEFAULT_MASTER_DS;
}
