package com.github.byference.controller;

import com.github.byference.annotation.*;
import com.github.byference.service.TinyService;

/**
 * TinyController
 *
 * @author byference
 * @since 2019/03/17
 */
@TinyController
@TinyRequestMapping("/tiny")
public class TinyControllerDemo {

    @TinyAutowired
    private TinyService tinyService;


    @TinyRequestMapping("/hello")
    public String hello(@TinyRequestParam("name") String name, @TinyRequestParam("message") String message) {

        return tinyService.hello(name, message);
    }

    @TinyRequestMapping("/echo")
    public String echo() {

        return tinyService.echo();
    }

}
