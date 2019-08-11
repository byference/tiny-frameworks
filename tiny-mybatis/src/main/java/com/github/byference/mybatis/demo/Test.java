package com.github.byference.mybatis.demo;

import com.alibaba.fastjson.JSON;
import com.github.byference.mybatis.core.session.defaults.DefaultTinySqlSession;
import com.github.byference.mybatis.core.session.TinySqlSessionFactory;
import com.github.byference.mybatis.core.session.TinySqlSessionFactoryBuilder;
import com.github.byference.mybatis.entity.UserInfo;
import com.github.byference.mybatis.mapper.UserInfoMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author byference
 * @since 2019-07-29
 */
public class Test {


    public static void main(String[] args) throws IOException {

        // 将配置文件读取为stream
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");

        // 通过读取的stream构建 SqlSessionFactory
        TinySqlSessionFactory sqlSessionFactory = new TinySqlSessionFactoryBuilder().build(inputStream);

        // 从 SqlSessionFactory 中获取 SqlSession
        DefaultTinySqlSession sqlSession = sqlSessionFactory.openSession();

        // 从SqlSession中获取mapper实例（代理）
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);

        // 调用mapper方法
        UserInfo userInfo = mapper.selectByIdAndName(1, "joy");

        // 打印输出
        System.out.println(JSON.toJSONString(userInfo));

    }
}
