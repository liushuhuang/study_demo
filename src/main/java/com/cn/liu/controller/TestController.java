package com.cn.liu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.liu.annotation.RateLimiter;
import com.cn.liu.entity.test.User;
import com.cn.liu.json.ResponseResult;
import com.cn.liu.service.TestService;
import com.dyms.encrypt.annotation.encrypt.EncryptBody;
import com.dyms.encrypt.enums.EncryptBodyMethod;
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
    @RequestMapping ("/tree")
    public ResponseResult test2(){
        return testService.test2();
    }

    @PostMapping("/mq & rateLimit")
    @RateLimiter(key = "test1",time = 5,count = 1000)
    public void test1(@RequestBody Map<String,Object> map){
        testService.test1(map);
    }
    @PostMapping("/mq-topic")
    public void test3(@RequestBody Map<String,Object> map){
        testService.test3(map);
    }

    @PostMapping("/mq-queue")
    public void test4(@RequestBody Map<String,Object> map){
        testService.test4(map);
    }

    @PostMapping("/test5")
    public void test5(@RequestBody Map<String,Object> map){
        JSONObject user1 = JSONObject.parseObject(JSON.toJSONString(map.get("role")));
        User user = JSONObject.toJavaObject(user1, User.class);
        System.out.println(user.toString());
    }

    @PostMapping("/test6")
    @EncryptBody(value = EncryptBodyMethod.SM4)
    public ResponseResult test6(){
        return ResponseResult.success();
    }
}
