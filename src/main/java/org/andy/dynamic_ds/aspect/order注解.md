
@Order(value)
其中的value值越低，说明优先级越高。

在DataSourceAspect切面类和GlobalDataSourceAspect切面类都分别标识了@Order注解，前者是给的值是11，后者给的值是10；
这样的话就实现了 GlobalDataSourceAspect 将会比 DataSourceAspect 更优先执行；
那么，这就实现了我们想要的效果，那就是：当方法中存在@DataSource时，我们使用方法上的定义的数据源；反之，我们使用全局定义的数据源；