package com.zhangkaigang.base.log;

import java.lang.annotation.*;

/**
 * @Description:自定义注解，在pojo上使用该注解，通过反射得到注解字段
 * @Author:zhang.kaigang
 * @Date:2019/6/27
 * @Version:1.0
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.FIELD, ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented//说明该注解将被包含在javadoc中
public @interface FieldMeta {

    /**
     * 描述
     * @return
     */
    String name() default "";

    /**
     * 枚举值类型
     * @return
     */
    String dictKey() default "";
}
