package com.github.byference.controller;

import com.github.byference.annotation.TinyAutowired;
import com.github.byference.annotation.TinyController;
import com.github.byference.annotation.TinyRequestMapping;
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
    public String hello (String name) {

        return tinyService.echo(name);
    }

}
