package com.cn.liu.service;

import com.cn.liu.json.ResponseResult;

import java.util.Map;

/**
 * @author liushuhuang
 * @date 2023/2/21
 */
public interface TestService {

    /**
     *  测试amq（秒杀测试）
     */
    public void test1(Map<String,Object> map);
    public void init();

    /**
     * 测试类型转换
     * @param map
     */
    public void test3(Map<String,Object> map);

    /**
     * 测试类型转换
     * @param map
     */
    public void test4(Map<String,Object> map);

    /**
     * 测试树形结构
     * @return
     */
    public ResponseResult test2();

}
