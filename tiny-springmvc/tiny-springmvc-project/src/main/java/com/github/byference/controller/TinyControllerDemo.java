package com.github.byference.controller;

import com.github.byference.annotation.TinyController;
import com.github.byference.annotation.TinyRequestMapping;

/**
 * TinyController
 *
 * @author byference
 * @since 2019/03/17
 */
@TinyController
@TinyRequestMapping("/tiny")
public class TinyControllerDemo {


    @TinyRequestMapping("/hello")
    public String hello (String name) {

        return "hello, " + name;
    }

}
