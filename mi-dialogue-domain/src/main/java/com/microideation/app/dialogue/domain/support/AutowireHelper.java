package com.microideation.app.dialogue.domain.support;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by sandheepgr on 25/5/17.
 */
public final class AutowireHelper implements ApplicationContextAware {

    private static final AutowireHelper INSTANCE = new AutowireHelper();
    private static ApplicationContext applicationContext;

    private AutowireHelper() {
    }

    /**
     * Tries to autowire the specified instance of the class if one of the specified beans which need to be autowired
     * are null.
     *
     * @param classToAutowire the instance of the class which holds @Autowire annotations
     * @param beansToAutowireInClass the beans which have the @Autowire annotation in the specified {#classToAutowire}
     */
    public static void autowire(Object classToAutowire, Object... beansToAutowireInClass) {
        for (Object bean : beansToAutowireInClass) {
            if (bean == null) {
                applicationContext.getAutowireCapableBeanFactory().autowireBean(classToAutowire);
                return;
            }
        }
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        AutowireHelper.applicationContext = applicationContext;
    }

    /**
     * @return the singleton instance.
     */
    public static AutowireHelper getInstance() {
        return INSTANCE;
    }

}