package com.github.byference.servlet;


import com.github.byference.annotation.*;
import com.github.byference.handler.HandlerAdapter;
import reactor.core.publisher.Mono;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.byference.util.DefaultPrintUtil.printError;
import static com.github.byference.util.DefaultPrintUtil.println;

/**
 * TinyDispatcherServlet
 *
 * @author byference
 * @since 2019/03/16
 */
public class TinyDispatcherServlet extends HttpServlet {

    private Properties properties = new Properties();

    private List<String> classNames = new ArrayList<>();

    private Map<String, Object> beans = new ConcurrentHashMap<>();

    private Map<String, Method> handlerMapping = new ConcurrentHashMap<>();

    private Map<String, Object> controllerMap = new ConcurrentHashMap<>();


    @Override
    public void init(ServletConfig config) {

        // 加载配置文件
        loadConfig(config.getInitParameter("contextConfigLocation"));

        // 扫描 basePackage 下面所有的class
        scanner(properties.getProperty("scanPackage"));

        // beans 容器初始化
        instance();

        // 注入Bean
        autowired();

        // 初始化HandlerMapping
        tinyHandlerMapping();

        // 初始化完成
        System.out.printf("%s --- [%s] initialization complete.\n", LocalDateTime.now(), Thread.currentThread().getName());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            tinyDispatcherServlet(request, response);
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().write("500: server error!");
        }
    }

    private void tinyDispatcherServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        Mono.just(request.getRequestURI()).doOnNext(uri -> {
            if (Objects.equals("/favicon.ico", uri)) {
                throw new IllegalArgumentException("no favicon.ico found!");
            }
            if (!handlerMapping.containsKey(uri)) {
                throw new IllegalArgumentException("404: not found!");
            }
        }).map(uri -> {
            Method method = handlerMapping.get(uri);
            HandlerAdapter ha = (HandlerAdapter) beans.get("paramHandlerAdapter");
            Object[] args = ha.handle(request, response, method, beans);

            Object result;
            try {
                result = method.invoke(controllerMap.get(uri), args);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
            return result;
        }).doOnNext(result -> {
            out.write(String.valueOf(result));
            println("success");
        }).doOnError(e -> {
            printError(e.getMessage());
            out.write(e.getMessage());
        }).subscribe();
    }

    private void tinyHandlerMapping() {

        if (!beans.isEmpty()) {
            beans.forEach((k, v) -> {
                Class<?> clazz = v.getClass();
                if (clazz.isAnnotationPresent(TinyController.class)) {
                    String baseUrl = "";
                    if (clazz.isAnnotationPresent(TinyRequestMapping.class)) {
                        TinyRequestMapping tinyRequestMapping = clazz.getAnnotation(TinyRequestMapping.class);
                        baseUrl = tinyRequestMapping.value();
                    }
                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        String url = baseUrl;
                        if (!method.isAnnotationPresent(TinyRequestMapping.class)) {
                            continue;
                        }
                        TinyRequestMapping tinyRequestMapping = method.getAnnotation(TinyRequestMapping.class);
                        url += "/" + tinyRequestMapping.value();
                        url = url.replaceAll("/+", "/");
                        handlerMapping.put(url, method);
                        try {
                            String name = lowerFirst(clazz.getSimpleName());
                            Object instance;
                            if (beans.containsKey(name)) {
                                instance = beans.get(name);
                            } else {
                                instance = clazz.newInstance();
                                beans.put(name, instance);
                            }
                            controllerMap.put(url, instance);
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }

    private void autowired() {

        if (beans.isEmpty()) {
            System.err.printf("%s --- [%s] autowired failed.\n", LocalDateTime.now(), Thread.currentThread().getName());
            return;
        }
        beans.forEach((k, v) -> {
            Class<?> clazz = v.getClass();
            if (clazz.isAnnotationPresent(TinyController.class)) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(TinyAutowired.class)) {

                        String name = "";
                        if (field.isAnnotationPresent(TinyQualifier.class)) {
                            TinyQualifier qualifier = field.getAnnotation(TinyQualifier.class);
                            name = qualifier.value();
                        }
                        if (Objects.equals("", name)) {
                            name = field.getName();
                        }
                        field.setAccessible(true);
                        try {
                            field.set(v, beans.get(name));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }

    private void instance() {
        if (!classNames.isEmpty()) {
            classNames.forEach(name -> {
                try {
                    Class<?> clazz = Class.forName(name);
                    if (clazz.isAnnotationPresent(TinyController.class)) {
                        beans.put(lowerFirst(clazz.getSimpleName()), clazz.newInstance());
                    }
                    if (clazz.isAnnotationPresent(TinyComponent.class)) {

                        TinyComponent tinyComponent = clazz.getAnnotation(TinyComponent.class);
                        String beanName = tinyComponent.value();
                        beans.put(beanName, clazz.newInstance());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void loadConfig(String config) {

        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(config)) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void scanner(String basePackage) {

        URL resource = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", "/"));
        File dir = new File(Objects.requireNonNull(resource).getFile());
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                scanner(basePackage + "." + file.getName());
            } else {
                String className = basePackage + "." + file.getName().replaceAll(".class", "");
                classNames.add(className);
            }
        }
    }

    private static String lowerFirst(String name) {
        char[] chars = name.toCharArray();
        if (Character.isUpperCase(chars[0])) {
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }

}
