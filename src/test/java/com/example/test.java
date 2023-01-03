package com.example;

import com.cn.liu.entity.User;
import com.cn.liu.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class test {
    @Autowired
    UserMapper userMapper;


    @Test
    public void testdatesource(){


        User users = userMapper.selectUserById(1);
    }
}
