package io.github.byference.demo.service;

import io.github.byference.spring.stereotype.TinyComponent;

import java.time.LocalDateTime;

/**
 * UserService
 *
 * @author byference
 * @since 2020-11-15
 */
@TinyComponent
public class UserService {

    public String echo() {
        return "UserService echo:" + LocalDateTime.now().toString();
    }
}
