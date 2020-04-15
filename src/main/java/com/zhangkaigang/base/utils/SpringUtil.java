package com.zhangkaigang.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description:获取spring上下文里的bean，方便在普通类里也可以使用
 * @Author:zhang.kaigang
 * @Date:2019/6/26
 * @Version:1.0
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBeanByName(String name) {
        return getApplicationContext().getBean(name);
    }

    public static Object getBeanByClass(Class c) {
        return getApplicationContext().getBean(c);
    }

}
