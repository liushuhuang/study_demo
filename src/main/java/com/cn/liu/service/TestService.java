package com.cn.liu.service;

import com.cn.liu.Json.ResponseResult;
import com.cn.liu.entity.TreeNode;

import java.util.List;
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

    public ResponseResult test2();
}
