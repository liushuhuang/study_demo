package com.cn.liu.controller;

import com.cn.liu.json.ResponseResult;
import com.cn.liu.annotation.RateLimiter;
import com.cn.liu.service.TestService;
import org.springframework.web.bind.annotation.*;

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
}
