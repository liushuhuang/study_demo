package com.cn.liu.service;

import com.cn.liu.json.ResponseResult;

import java.util.Map;

/**
 * @author liushuhuang
 * @date 2023/2/21
 */
public interface TestService {

    /**
     *  测试amq
     */
    public void test1(Map<String,Object> map);
    public void init();
    public void test3(Map<String,Object> map);
    public void test4(Map<String,Object> map);
    public ResponseResult test2();
}
