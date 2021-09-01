package com.zjx.demo.processEngine;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author 65454
 */
@Component
public class BeanService implements ApplicationContextAware {

    protected static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanService.applicationContext = applicationContext;
    }

    public static Object getBeanByName(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getSingleBeanByType(Class<T> clazz) throws Exception {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            Object beanByName = getBeanByName(beanName);
            if (Objects.nonNull(beanByName)) {
                //防止被代理导致拿不到bean
                Object target = AopTargetUtil.getTarget(beanByName);
                if (clazz.getName().equals(target.getClass().getName())) {
                    return (T) beanByName;
                }
            }
        }
        throw new RuntimeException();
    }
}
