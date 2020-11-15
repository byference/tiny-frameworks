package io.github.byference.spring.context;

/**
 * TinyAbstractApplicationContext
 *
 * @author byference
 * @since 2020-09-26
 */
public abstract class TinyAbstractApplicationContext implements AutoCloseable, TinyApplicationContext {

    private TinyDefaultListableBeanFactory beanFactory;

    /**
     * refresh application context
     */
    abstract void refresh();

    @Override
    public void close() throws Exception {

    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return beanFactory.getBean(requiredType);
    }
}
