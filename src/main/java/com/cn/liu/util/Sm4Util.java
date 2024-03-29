package com.cn.liu.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.security.Security;

/**
 * 国密SM4分组密码算法工具类（对称加密）
 * <p>GB/T 32907-2016 信息安全技术 SM4分组密码算法</p>
 * <p>SM4-ECB-PKCS5Padding</p>
 * @author liu
 */
@Component
public class Sm4Util {

  private static final String ALGORITHM_NAME = "SM4";
  private static final String ALGORITHM_ECB_PKCS5PADDING = "SM4/ECB/PKCS5Padding";
  private static final String SM4_KEY1 = "TXfuJtf5Jzdl1cwF6TeYKIytxd7JjstuFS25CSGEbAPLDI8u";

  private static final String SM4_KEY = SM4_KEY1.substring(0,16);

  public static String a;
  @Value(value = "${server.port}")
  public void setA(String b) {
    a = b;
  }

  /**
   * SM4算法目前只支持128位（即密钥 16 字节）
   */
  private static final int DEFAULT_KEY_SIZE = 128;

  static {
    // 防止内存中出现多次BouncyCastleProvider的实例
    if (null == Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) {

      Security.addProvider(new BouncyCastleProvider());
    }
  }

  private Sm4Util() {
  }

  /**
   * 生成密钥
   * <p>建议使用DigestUtil.binToHex将二进制转成HEX</p>
   *
   * @return 密钥16位
   * @throws Exception 生成密钥异常
   */
  public static byte[] generateKey() throws Exception {
    KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
    kg.init(DEFAULT_KEY_SIZE, new SecureRandom());
    return kg.generateKey().getEncoded();
  }

  /**
   * 加密，SM4-ECB-PKCS5Padding
   *
   * @param data 要加密的明文
   * @return 加密后的密文
   * @throws Exception 加密异常
   */
  public static byte[] encrypt(byte[] data) throws Exception {
    return sm4(data, StrUtil.hexToBin(SM4_KEY), Cipher.ENCRYPT_MODE);
  }

  /**
   * 解密，SM4-ECB-PKCS5Padding
   *
   * @param data 要解密的密文
   * @return 解密后的明文
   * @throws Exception 解密异常
   */
  public static byte[] decrypt(byte[] data) throws Exception {
    return sm4(data, StrUtil.hexToBin(SM4_KEY), Cipher.DECRYPT_MODE);
  }

  /**
   * SM4对称加解密
   *
   * @param input 明文（加密模式）或密文（解密模式）
   * @param key   密钥
   * @param mode  Cipher.ENCRYPT_MODE - 加密；Cipher.DECRYPT_MODE - 解密
   * @return 密文（加密模式）或明文（解密模式）
   * @throws Exception 加解密异常
   */
  private static byte[] sm4(byte[] input, byte[] key, int mode)
      throws Exception {
    SecretKeySpec sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
    Cipher cipher = Cipher
        .getInstance(ALGORITHM_ECB_PKCS5PADDING, BouncyCastleProvider.PROVIDER_NAME);
    cipher.init(mode, sm4Key);
    return cipher.doFinal(input);
  }
}


