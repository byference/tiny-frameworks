package com.github.byference.service.impl;

import com.github.byference.annotation.TinyComponent;
import com.github.byference.service.TinyService;

/**
 * @author byference
 * @since 2019/03/20
 */
@TinyComponent("tinyService")
public class TinyServiceImpl implements TinyService {

    @Override
    public String echo() {
        return "echo";
    }

    @Override
    public String hello(String name, String message) {

        return String.format("hello %s, %s", name, message);
    }

}
