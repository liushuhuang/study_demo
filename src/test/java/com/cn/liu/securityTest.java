package com.cn.liu;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.cn.liu.util.Sm4Util;
import com.cn.liu.util.security.sm2.SecretKeyUtils;
import com.cn.liu.util.security.sm4.SM2Util;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class securityTest {

    @Test
    public void test1() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("param1","a");
        map.put("param2","b");
        map.put("param3","c");
        map.put("param4","d");
        map.put("param5","e");
        //String a = String.valueOf(Sm4Util.encrypt(JSON.toJSONString(map).getBytes()));
        SecretKeyUtils.generatePaKey();
        //System.out.println(SecretKeyUtils.generateSm2KeyPair().getPrivate().toString());


    }
}
