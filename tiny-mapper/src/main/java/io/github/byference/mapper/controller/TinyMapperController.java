package io.github.byference.mapper.controller;

import io.github.byference.mapper.mapper.UserInfoMapper;
import io.github.byference.mapper.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TinyMapperController
 *
 * @author byference
 * @since 2020-08-30
 */
@RestController
@RequestMapping("tiny_mapper")
public class TinyMapperController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @PostMapping("insert")
    public Integer insert(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @PostMapping("updateById")
    public Integer updateById(UserInfo userInfo) {
        return userInfoMapper.updateById(userInfo);
    }

    @PostMapping("deleteById")
    public Integer deleteById(Integer id) {
        return userInfoMapper.deleteById(id);
    }

    @GetMapping("findById")
    public UserInfo findById(Integer id) {
        return userInfoMapper.findById(id);
    }
}
