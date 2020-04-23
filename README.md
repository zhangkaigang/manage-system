# manage-system

## FAQ
### 1.IdWorker生成id返回前台精度问题
JavaScript无法处理Java的长整型Long导致精度丢失，解决办法Long转为String返回  
方法1.在Long类型的属性加上JsonFormat注解（JackJson ）
```
@JsonFormat(shape = JsonFormat.Shape.STRING)
```  
方法2.在Long类型的属性加上JsonSerialize注解（JackJson ）
```
@JsonSerialize(using=ToStringSerializer.class)
```
方法3.Spring MVC自定义消息转换器
编写自定义类 com.zhangkaigang.base.config.CustomObjectMapper
springmvc.xml中配置
```
<mvc:annotation-driven>
        <mvc:message-converters>
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg index="0" value="utf-8" />
                <property name="supportedMediaTypes">
                    <list>
                        <value>application/json;charset=UTF-8</value>
                        <value>text/plain;charset=UTF-8</value>
                    </list>
                </property>
            </bean>
            <bean
                    class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="com.zhangkaigang.base.config.CustomObjectMapper">

                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
```
方法4.如果是springboot项目，可以全局配置，参考以下（未测试）  
https://blog.csdn.net/zhuanhui0116/article/details/83186187
https://baomidou.oschina.io/mybatis-plus-doc/#/question 问题6


