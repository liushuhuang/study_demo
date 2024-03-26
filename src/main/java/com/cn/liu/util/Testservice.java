package com.cn.liu.util;

/**
 * @author liushuhuang
 * @date 2023/11/14
 */
public class Testservice {
    public void test1(){
        System.out.println("之前");

        System.out.println("之后");
    }

    public void test2(MyTestEvent testEvent){
        System.out.println("测试");

    }
}
