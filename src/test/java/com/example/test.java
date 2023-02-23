package com.example;

import com.cn.liu.entity.User;
import com.cn.liu.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class test {
    @Autowired
    UserMapper userMapper;


    @Test
    public void testdatesource(){


        User users = userMapper.selectUserById(1);
    }

    @Test
    public void test() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:/11111.txt");
        byte[] bytes = null;
        for (int i = 1; i < 1001; i++) {
            String msg =String.valueOf(i)+","+"liu"+String.valueOf(i);
            bytes = msg.getBytes();
            fileOutputStream.write(bytes);
            bytes = "\r\n".getBytes();
            fileOutputStream.write(bytes);
        }

        System.out.println("1111");
    }
}
