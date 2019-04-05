package com.github.byference.tinyrpc.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import com.github.byference.tinyrpc.annotation.TinyReference;
import com.github.byference.tinyrpc.provider.api.UserService;
import com.github.byference.tinyrpc.consumer.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author byference
 * @since 2019/03/31
 */
@Slf4j
@RestController
public class UserController {

    @TinyReference("userService")
    private UserService userService;


    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {

        log.info("access getUserById: {}", id);
        return userService.getUserById(id);
    }

}
