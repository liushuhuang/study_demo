package com.cn.liu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.liu.Json.ResponseResult;
import com.cn.liu.annotation.RateLimiter;
import com.cn.liu.entity.TreeNode;
import com.cn.liu.entity.User;
import com.cn.liu.service.TestService;
import org.springframework.mail.MailParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
    @PostMapping("/test2")
    public ResponseResult test2(){
        return testService.test2();
    }

    @PostMapping("/test1")
    @RateLimiter(key = "test1",time = 5,count = 1000)
    public void test1(@RequestBody Map<String,Object> map){
        testService.test1(map);
    }

}
