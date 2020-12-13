package io.github.byference.spring.context;

import io.github.byference.spring.beans.TinyBeansException;
import io.github.byference.spring.beans.factory.Aware;

/**
 * TinyApplicationContextAware
 *
 * @author byference
 * @since 2020-12-13
 */
public interface TinyApplicationContextAware extends Aware {

    void setTinyApplicationContext(TinyApplicationContext applicationContext) throws TinyBeansException;

}
