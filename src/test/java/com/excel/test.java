package com.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.List;


/**
 * @author liushuhuang
 * @date 2023/9/26
 */
public class test {
    @Test
    public void test(){
        List<Object> objects = EasyExcel.read("", User.class, new userListener()).sheet().doReadSync();
    }
}
