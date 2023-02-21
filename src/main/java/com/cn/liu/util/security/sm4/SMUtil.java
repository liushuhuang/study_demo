package com.cn.liu.util.security.sm4;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

public class SMUtil {

    public static String SM4Encode(Map<String, Object> resultMap,String secret) throws HttpMessageNotWritableException {
        //String secret = "TXfuJtf5Jzdl1cwF6TeYKIytxd7JjstuFS25CSGEbAPLDI8u";
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

    public static String SM4Dncode(Map<String, Object> resultMap,String secret) throws HttpMessageNotWritableException {
        //String secret = "TXfuJtf5Jzdl1cwF6TeYKIytxd7JjstuFS25CSGEbAPLDI8u";
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

    public static String SM2Decode(String privateckey, String plainText) throws Exception {
        return SM2Util.decryptData(privateckey,plainText);
    }

    public static String SM2Eecode(String publickey, String plainText) throws Exception {
        return SM2Util.encryptData(publickey,plainText);
    }


}
