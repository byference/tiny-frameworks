package io.github.byference.demo.controller;

import io.github.byference.demo.service.UserService;
import io.github.byference.spring.stereotype.TinyAutowired;
import io.github.byference.spring.stereotype.TinyComponent;

/**
 * TinyUserController
 *
 * @author byference
 * @since 2020-11-15
 */
@TinyComponent
public class UserController {

    @TinyAutowired
    private UserService userService;


}
