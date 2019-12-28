package com.github.byference.tinyrpc.consumer.controller;

import com.github.byference.tinyrpc.provider.api.UserService;
import com.github.byference.tinyrpc.provider.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author byference
 * @since 2019/03/31
 */
@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
}
