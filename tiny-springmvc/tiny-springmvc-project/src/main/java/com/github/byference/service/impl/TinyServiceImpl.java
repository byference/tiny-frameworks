package com.github.byference.service.impl;

import com.github.byference.annotation.TinyComponent;
import com.github.byference.service.TinyService;

/**
 * @author byference
 * @since 2019/03/20
 */
@TinyComponent
public class TinyServiceImpl implements TinyService {

    @Override
    public String echo(String name) {
        return "echo -> " + name;
    }

}
