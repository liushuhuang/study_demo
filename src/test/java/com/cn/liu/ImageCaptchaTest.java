package com.cn.liu;

import com.cn.liu.util.ImageCaptchaParams;
import com.cn.liu.util.ImageCaptchaResult;
import com.cn.liu.util.ImageCaptchaUtil;
import com.cn.liu.util.security.sm2.SecretKeyUtils;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileOutputStream;
import java.security.KeyPair;

/**
 * @author huangrusheng
 * @version 1.0
 * @date 2021/5/20 10:13
 */
@RunWith(JUnit4.class)
public class ImageCaptchaTest {

    @Test
    public void testCreate() throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\image_captcha.png");
        System.out.println(ImageCaptchaUtil.create(new ImageCaptchaParams(),fileOutputStream));
        fileOutputStream.close();
    }
    @Test
    public void testCreateBase64() throws Exception{
       ImageCaptchaResult result =  ImageCaptchaUtil.createBase64(new ImageCaptchaParams());
       System.out.println(result.getWords());
       System.out.println(result.getReturnContent());
    }
    @Test
    public void testCreateBase64ForWeb() throws Exception{
        ImageCaptchaResult result =  ImageCaptchaUtil.createBase64ForWeb(new ImageCaptchaParams());
        System.out.println(result.getWords());
        System.out.println(result.getReturnContent());
    }

    @Test
    public void generateSm2KeyPairTest() throws Exception {
        KeyPair keyPair = SecretKeyUtils.generateSm2KeyPair();
        String privateKey = Base64.encodeBase64String(keyPair.getPrivate().getEncoded());
        String publicKey = Base64.encodeBase64String(keyPair.getPublic().getEncoded());
        System.out.println("privateKey:-----------------------------------------");
        System.out.println(privateKey);
        System.out.println("publicKey:------------------------------------------");
        System.out.println(publicKey);
    }
}
