package io.github.byference.demo.service;

import io.github.byference.spring.beans.TinyBeansException;
import io.github.byference.spring.context.TinyApplicationContext;
import io.github.byference.spring.context.TinyApplicationContextAware;
import io.github.byference.spring.stereotype.TinyComponent;

import java.time.LocalDateTime;

/**
 * UserService
 *
 * @author byference
 * @since 2020-11-15
 */
@TinyComponent
public class UserService implements TinyApplicationContextAware {

    private TinyApplicationContext applicationContext;

    public String echo() {
        return "UserService echo: " + LocalDateTime.now().toString();
    }

    @Override
    public void setTinyApplicationContext(TinyApplicationContext applicationContext) throws TinyBeansException {
        System.out.println("setApplicationContext success...");
        this.applicationContext = applicationContext;
    }
}
