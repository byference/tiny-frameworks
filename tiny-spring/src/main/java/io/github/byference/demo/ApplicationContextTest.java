package io.github.byference.demo;

import io.github.byference.demo.controller.UserController;
import io.github.byference.spring.context.TinyAnnotationConfigApplicationContext;
import org.junit.Test;

/**
 * ApplicationContextTest
 *
 * @author byference
 * @since 2020-10-08
 */
public class ApplicationContextTest {


    @Test
    public void applicationContextTest() throws Exception {

        TinyAnnotationConfigApplicationContext applicationContext = new TinyAnnotationConfigApplicationContext();

        // 第二步 注册
        applicationContext.register(ApplicationContextTest.class);

        applicationContext.refresh();

        UserController userController = applicationContext.getBean(UserController.class);
        System.out.println(userController.echo());

        applicationContext.close();
    }


}
