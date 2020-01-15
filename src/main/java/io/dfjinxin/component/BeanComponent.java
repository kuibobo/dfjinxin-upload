package io.dfjinxin.component;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * BeanComponent Bean操作组件
 */
@Component("beanComponent")
public class BeanComponent {

    private static ApplicationContext applicationContext;

    public BeanComponent(ApplicationContext context) {
        BeanComponent.applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> t) {
        return applicationContext.getBean(t);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
}
