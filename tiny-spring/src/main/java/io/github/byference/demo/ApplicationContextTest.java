package io.github.byference.demo;

import io.github.byference.demo.controller.UserController;
import io.github.byference.demo.service.UserService;
import io.github.byference.spring.context.TinyAnnotationConfigApplicationContext;
import org.junit.Test;

import java.util.Map;

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

        final String[] beanNamesForType = applicationContext.getBeanNamesForType(Object.class);
        System.out.println(beanNamesForType);
        final Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);
        System.out.println(beansOfType);
        final Map<String, UserService> UserServiceMap = applicationContext.getBeansOfType(UserService.class);
        System.out.println(UserServiceMap);

        applicationContext.close();
    }


}
