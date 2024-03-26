package com.cn.liu.controller;

import com.dyms.encrypt.bean.RedisCache;
import com.dyms.encrypt.enums.Constants;
import com.dyms.encrypt.util.security.sm.Sm2Util;
import com.dyms.encrypt.util.security.smutil.SM.SM.SMUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class Sm2Controller {
    Sm2Util sm2Util = new Sm2Util();

    @Autowired
    private RedisCache redisCache;

    @Value(value = "${priKey}")
    private String priKey;


    @PostMapping("/sm4key.do")
    public Map<String,String> sm4(@RequestBody Map<String, String> map) throws Exception {
        Map<String,String> ajax = new HashMap<>();
        String pubKey = map.get("pubKey");
        String cipher = map.get("cipher");
        String uuid = "1111";
        String sm4key = SMUtil.SM2Decode(priKey, cipher);
        redisCache.setCacheObject(Constants.SM4_KEY+uuid, sm4key, Constants.SM4_EXPIRE_TIME, TimeUnit.HOURS);
        ajax.put("encryptId", uuid);
        return ajax;

    }

}
