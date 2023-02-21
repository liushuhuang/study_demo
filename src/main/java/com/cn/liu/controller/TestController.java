package com.cn.liu.controller;

import com.cn.liu.service.TestService;
import org.springframework.mail.MailParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author liushuhuang
 * @date 2023/2/21
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    TestService testService;


    @PostMapping("/test1")
    public void test1(@RequestBody Map<String,String> map){
        testService.test1(map.get("str"));
    }

}
