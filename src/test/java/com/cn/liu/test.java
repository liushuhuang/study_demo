package com.cn.liu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cn.liu.util.security.sm2.SecretKeyUtils;
import com.cn.liu.util.security.sm4.SM2Util;
import com.cn.liu.util.security.sm4.SM4Util;
import com.cn.liu.util.security.sm4.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServerHttpAsyncRequestControl;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class test {


    @Test
    public void test1() throws Exception {
        String key = "TXfuJtf5Jzdl1cwF6TeYKIytxd7JjstuFS25CSGEbAPLDI8u";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("param1","a");
        map.put("param2","b");
        map.put("param3","c");
        map.put("param4","d");
        map.put("param5","e");
        User user = new User(1,23,'m');

//        String sm4Key = msg.substring(0, 16);
//        String hash = msg.substring(16);
        String sourceHash = ""; //前端加密报文
    //    msg = SmSecurityUtil.decodeSm4(msg, sm4Key);
        String respString=encodeResp(map);
        String respString1=encodeResp1(user);
        System.out.println(respString);

    }


    @Test
    public void test2() throws Exception {
        String key = "TXfuJtf5Jzdl1cwF6TeYKIytxd7JjstuFS25CSGEbAPLDI8u";
        String sm4Key = key.substring(0, 16);
        //
        String reqStr = "a8c8945b175414f4ddfcdd42975408fcb47160e9fdb9df583e82e985fda0d73c9d7bb1c88c1d49cfc5f6670a8028c62a5b628e503f3897b10ab52ffe070cc06b";
        System.out.println(reqStr.substring(64));
        String msg1 = decode("0a5ae89d65a8b31eb3b671276c3d0d8af6e6663ed5c9b0f7fcdeb07a3522ffc85fc09e68a90e0d6f31173cf705757bde3d39cc87541f4f319eb4aee1364c6dcb9378b541c349136538934321a946058c", sm4Key);
        //String msg1 = SmSecurityUtil.decodeSm4(reqStr.substring(67), sm4Key);
        System.out.println(msg1);



    }

    @Test
    public void test3() throws Exception {
        String key = "2e103dab40e7bcfbba86134a0c683576a6c88995c5462a371c2587f6857eedd8";
        //Hex.decode(key);
        System.out.println(key.substring(67));



    }

    @Test
    public void test4() throws Exception {
//        String key = "2e103dab40e7bcfbba86134a0c683576a6c88995c5462a371c2587f6857eedd8";
//        //Hex.decode(key);
//        System.out.println(key.substring(67));
        String publickey = "040b03c79075a2c294a9ceb67fc831b809efa7564284219e7b9d4cec9b8bf1327e038f329023e68a19b5878fc782c0cd5f2d7bc5222f4b5ee89a303680e97db5a6";
        String privateckey = "ced784a3a13e08e3c9983247bb7f3486e23524106058557e5ec9048e90742ca";
        String date = "041ea6a09d132b821de9e79517368e416493e2de3529168eece185f99a636357244e2f932587488760deb3056c6f1bb1643cc4d7104f705ec798ca5497b748b9677c2784022e439086263f8c99e8f52d4db3cb90e45dd2285103089b1d4ff4e450222183f925fb854c98d8784352e3933daec5320ba346f53429db68cea4bf9d86eb9c2ae5149a6fa8e68e7b9b3f3a44294e484d79d16a22df8dcb1a63dc4692616525";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("param1","a");
        map.put("param2","b");
        map.put("param3","c");
        map.put("param4","d");
        map.put("param5","e");
        User user = new User(1,23,'m');
        String encode = SM2Util.encryptData(publickey,JSON.toJSONString(user));
        System.out.println(encode);
        String decode = SM2Util.decryptData(privateckey,encode);
        System.out.println(decode);



    }


    public  String encodeResp(Map<String, Object> resultMap)
            throws IOException, HttpMessageNotWritableException {
        String secret = "TXfuJtf5Jzdl1cwF6TeYKIytxd7JjstuFS25CSGEbAPLDI8u";
        SerializerFeature[] features = new SerializerFeature[0];
        // 获取动态key值
        String key = secret;
        String text = "";
        try {
            // 判断是否加密，如果包含动态加密KEY则是加密请求，响应报文需要加密返回
            if (StringUtils.isNotBlank(key)) {
                // 移除动态KEY，防止将KEY响应给前端
                String tmpText = JSON.toJSONString(resultMap, features);
                //sm4的密钥
                String sm4Key = key;
                //计算摘要的盐值，默认与sm4key一致
                String salt = key;
                if(key.length() > 16) {
                    //盐值与sm4key不一致，需要截取
                    sm4Key = key.substring(0, 16);
                    salt = key.substring(16);
                }

                // 获取摘要
                String hash = SM4Util.getHashHex(salt + tmpText);
                // 加密响应报文
                //text = hash + SmSecurityUtil.encodeSm4(tmpText, sm4Key);
                text = hash + SM4Util.encod(tmpText, sm4Key);
            } else {
                return text;
            }
        } catch (Exception e) {
        }
        return text;
    }



    public  String encodeResp1(User user)
            throws IOException, HttpMessageNotWritableException {
        String secret = "TXfuJtf5Jzdl1cwF6TeYKIytxd7JjstuFS25CSGEbAPLDI8u";
        SerializerFeature[] features = new SerializerFeature[0];
        // 获取动态key值
        String key = secret;
        String text = "";
        try {
            // 判断是否加密，如果包含动态加密KEY则是加密请求，响应报文需要加密返回
            if (StringUtils.isNotBlank(key)) {
                // 移除动态KEY，防止将KEY响应给前端
                String tmpText = JSON.toJSONString(user, features);
                //sm4的密钥
                String sm4Key = key;
                //计算摘要的盐值，默认与sm4key一致
                String salt = key;
                if(key.length() > 16) {
                    //盐值与sm4key不一致，需要截取
                    sm4Key = key.substring(0, 16);
                    salt = key.substring(16);
                }
                // 获取摘要
                String hash = SM4Util.getHashHex(salt + tmpText);
                // 加密响应报文
                //text = hash + SmSecurityUtil.encodeSm4(tmpText, sm4Key);
                text = hash + SM4Util.encod(tmpText, sm4Key);
            } else {
                return text;
            }
        } catch (Exception e) {
        }
        return text;
    }

    public static String decode(String plainText, String sm4Key) throws Exception {
        if (plainText != null && !plainText.isEmpty()) {
            long startDate = System.currentTimeMillis();
            plainText = SM4Util.decrypt(plainText, sm4Key, Charset.forName("utf-8"));
//            if (logger.isDebugEnabled()) {
//                logger.debug("sm4解密耗时：" + (System.currentTimeMillis() - startDate));
//            }

            return plainText;
        } else {
            return plainText;
        }
    }

    @Test
    public void sss() throws Exception {
        System.out.println(SM2Util.encryptData("04fbe2dd2395a62ed4c736420e00b1a57d3e84d0eeb80b24514f4d73ac48bf124983a582e23d7d8cc948f779dcae3397961214adeeba185d786f40039ee37dcfc4","3Sgm_cyt"));
    }




}
