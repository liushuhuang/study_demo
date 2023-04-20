package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.liu.entity.User;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liushuhuang
 * @date 2023/4/20
 */
public class OkHttpTest {
    OkHttpClient client = new OkHttpClient();

    @Test
    public void test(){
        OkHttpClient client = new OkHttpClient();
        Map<String,Object> para = new HashMap<>();
        Map<String,Object> para2 = new HashMap<>();
        para.put("userName","liu");
        para.put("userId","1");
        para2.put("ss","liu");
        para2.put("aa","1");
        User user1 = new User(1,"a",2,"A","a");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body", para);
        jsonObject.put("ctrlDate", user1);
        jsonObject.put("aa",para2);

        RequestBody requestJsonBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());

        Request postRequest = new Request.Builder()
                .url("http://localhost:8081/test/test")
                .addHeader("Content-Type","applocation/json")
                .addHeader("x-gatway-host","localhost")
                .addHeader("token","1")
                .post(requestJsonBody)
                .build();
        try {
            Response response = client.newCall(postRequest).execute();
            String string = response.body().string();
            System.out.println(string);
            JSONObject object= JSONObject.parseObject(string);
            User user = JSONObject.toJavaObject((JSON) object.get("data"), com.cn.liu.entity.User.class);
            System.out.println("user:"+user);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
