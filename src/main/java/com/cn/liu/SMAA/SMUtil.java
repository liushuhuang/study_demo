package com.cn.liu.SMAA;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dyms.encrypt.bean.RedisCache;
import com.dyms.encrypt.exception.UtilException;
import com.dyms.encrypt.util.SpringUtils;
import com.dyms.encrypt.util.security.smutil.SM.SM.SM4Util;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author liu
 */
public class SMUtil {
    public static final String SM4_KEY = "sm4key:";
    /**
     * sm4key缓存时间(小时)
     */
    public static final Integer SM4_EXPIRE_TIME = 1;
    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    public static String SM4Encode(String content, String smid) throws Exception {
        //String secret = "TXfuJtf5Jzdl1cwF6TeYKIytxd7JjstuFS25CSGEbAPLDI8u";
        SerializerFeature[] features = new SerializerFeature[0];
        //获取密钥
        String secret = "";

        if(secret == null){
            throw new Exception("密钥为空");
        }
        // 获取动态key值
        String key = secret;
        String text = "";
        try {
            // 判断是否加密，如果包含动态加密KEY则是加密请求，响应报文需要加密返回
            if (StringUtils.isNotBlank(key)) {
                // 移除动态KEY，防止将KEY响应给前端
                String tmpText = JSON.toJSONString(content, features);
                //sm4的密钥
                String sm4Key = key;
                //计算摘要的盐值，默认与sm4key一致
                String salt = key;
                if (key.length() > 16) {
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

    public static String SM4Decode(String plainText, String smid) throws Exception {
        if (plainText.length() < 3) {
            throw new RuntimeException("bad message");
        }
        //获取密钥
        String sm4 = "";
        if(sm4 == null){
            throw new Exception("密钥为空");
        }
        //重置sm4key的时间
        SpringUtils.getBean(RedisCache.class).setCacheObject(SM4_KEY+smid, sm4, SM4_EXPIRE_TIME, TimeUnit.HOURS);
        String sm4Key = sm4.substring(0, 16);
        String sourceHash = plainText.substring(3, 67);
        String hash = sm4.substring(16);
        String msg = plainText.substring(67);

        if (msg != null && !msg.isEmpty()) {
            long startDate = System.currentTimeMillis();
            msg = SM4Util.decrypt(msg, sm4Key, Charset.forName("utf-8"));

            hash = SM4Util.getHashHex(hash + msg);
            if (!sourceHash.equals(hash)) {
                throw new UtilException("摘要不匹配");
            }
//            if (logger.isDebugEnabled()) {
//                logger.debug("sm4解密耗时：" + (System.currentTimeMillis() - startDate));
//            }

            return msg;
        } else {
            return msg;
        }
    }
}
