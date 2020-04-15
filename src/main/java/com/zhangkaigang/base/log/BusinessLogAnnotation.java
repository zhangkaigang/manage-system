package com.zhangkaigang.base.log;

import java.lang.annotation.*;

/**
 * @Description:业务日志注解类
 * @Author:zhang.kaigang
 * @Date:2019/6/24
 * @Version:1.0
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})//定义注解的作用目标**作用范围字段
@Documented//说明该注解将被包含在javadoc中
public @interface BusinessLogAnnotation {

    /**
     * 业务的名称,例如:"修改菜单"
     */
    String name() default "";

    /**
     * 资源ID，比如记录任务的日志，就传递任务主键，注意此id需要前台传递然后request拿到
     * @return
     */
    String key() default "";

}
