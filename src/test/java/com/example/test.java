package com.example;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.fastjson2.JSON;
import com.cn.liu.entity.User;
import com.cn.liu.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import javax.crypto.KeyGenerator;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;



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
    @Test
    public void test1(){
        Map<String, Object> claims = new HashMap<>();
        User user = new User();
        user.setSex("aaaaa");
        user.setName("liu");
        claims.put("aa", user);
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "abcdefghijklmnopqrstuvwxyz_dyms").compact();
        Claims body = Jwts.parser()
                .setSigningKey("abcdefghijklmnopqrstuvwxyz_dyms")
                .parseClaimsJws(token)
                .getBody();
        User uuid = (User) claims.get("aa");
        System.out.println(uuid);
    }

    @Test
    public void test2() throws Exception {
        String password = "password";
            ConfigTools.main(new String[]{password});
    }

}
