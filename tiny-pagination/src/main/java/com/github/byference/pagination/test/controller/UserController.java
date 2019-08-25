package com.github.byference.pagination.test.controller;

import com.github.byference.pagination.core.PageInfo;
import com.github.byference.pagination.test.pojo.UserInfo;
import com.github.byference.pagination.test.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User {@link RestController}
 *
 * @author byference
 * @since 2019-08-24
 */
@Slf4j
@RestController
@RequestMapping("page")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("start")
    public PageInfo<UserInfo> startPage() {

        List<UserInfo> userInfo = userService.getUserInfo(0, 2);

        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfo);
        log.info("pageInfo: {}", pageInfo);
        return pageInfo;
    }

}