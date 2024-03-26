package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cn.liu.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class test1 {
    @Resource
    UserMapper user1;
    @Test
    public void test3() throws Exception {
        String s = "{\n" +
                "    \"a\":{\n" +
                "        \"aaa\":\"1\",\n" +
                "        \"ccc\":\"1\"\n" +
                "    },\n" +
                "    \"userList\":[{\n" +
                "        \"a\":\"1\",\n" +
                "        \"b\":\"2\"\n" +
                "    },{\n" +
                "        \"a\":\"1\",\n" +
                "        \"b\":\"2\"\n" +
                "    },{\n" +
                "        \"a\":\"1\",\n" +
                "        \"b\":\"2\"\n" +
                "    }]\n" +
                "}";
        Map<String, Object> stringObjectMap = JSONObject.parseObject(s, new TypeReference<Map<String, Object>>() {
        });
        Map<String,Object> a = (Map<String, Object>) JSON.parse(stringObjectMap.get("a").toString());
        List<Map> userList = JSON.parseArray(stringObjectMap.get("userList").toString(), Map.class);
        System.out.println(userList);
        System.out.println(a);
    }
}



