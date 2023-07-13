package com.example;

import com.alibaba.druid.filter.config.ConfigTools;
import com.cn.liu.entity.User;
import com.cn.liu.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
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


    @Test
    public void test3() throws Exception {
        String input = "通用-参数[客户名称不能为纯数字]";
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        System.out.println(matcher.group(1));
    }

    @Test
    public void test4() throws Exception {
        BigDecimal a = BigDecimal.valueOf(1);
        BigDecimal b = BigDecimal.valueOf(1);
        System.out.println(a.compareTo(b));
    }
    @Test
    public void test5() throws Exception {
        Long a = 0L;
        System.out.println(a.compareTo(0L));
    }
}



