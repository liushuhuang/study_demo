package com.cn.liu.SMAA;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class SM4Util {
    //private static Logger logger = SysLogger.getLogger(SmSecurityUtil.class);
    public static final int[] SBOX_TABLE = new int[]{214, 144, 233, 254, 204, 225, 61, 183, 22, 182, 20, 194, 40, 251, 44, 5, 43, 103, 154, 118, 42, 190, 4, 195, 170, 68, 19, 38, 73, 134, 6, 153, 156, 66, 80, 244, 145, 239, 152, 122, 51, 84, 11, 67, 237, 207, 172, 98, 228, 179, 28, 169, 201, 8, 232, 149, 128, 223, 148, 250, 117, 143, 63, 166, 71, 7, 167, 252, 243, 115, 23, 186, 131, 89, 60, 25, 230, 133, 79, 168, 104, 107, 129, 178, 113, 100, 218, 139, 248, 235, 15, 75, 112, 86, 157, 53, 30, 36, 14, 94, 99, 88, 209, 162, 37, 34, 124, 59, 1, 33, 120, 135, 212, 0, 70, 87, 159, 211, 39, 82, 76, 54, 2, 231, 160, 196, 200, 158, 234, 191, 138, 210, 64, 199, 56, 181, 163, 247, 242, 206, 249, 97, 21, 161, 224, 174, 93, 164, 155, 52, 26, 85, 173, 147, 50, 48, 245, 140, 177, 227, 29, 246, 226, 46, 130, 102, 202, 96, 192, 41, 35, 171, 13, 83, 78, 111, 213, 219, 55, 69, 222, 253, 142, 47, 3, 255, 106, 114, 109, 108, 91, 81, 141, 27, 175, 146, 187, 221, 188, 127, 17, 217, 92, 65, 31, 16, 90, 216, 10, 193, 49, 136, 165, 205, 123, 189, 45, 116, 208, 18, 184, 229, 180, 176, 137, 105, 151, 74, 12, 150, 119, 126, 101, 185, 241, 9, 197, 110, 198, 132, 24, 240, 125, 236, 58, 220, 77, 32, 121, 238, 95, 62, 215, 203, 57, 72};
    public static final int[] FK = new int[]{-1548633402, 1453994832, 1736282519, -1301273892};
    public static final int[] CK = new int[]{462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};
    private static final Charset UTF8 = Charset.forName("utf-8");

    /**
     * 加密函数
     *
     * @param cipherText 需要加密的文本
     * @param sm4Key     sm4key
     * @return
     * @throws Exception
     */
    public static String encod(String cipherText, String sm4Key) throws Exception {
        if (cipherText != null && !cipherText.isEmpty()) {
            long startDate = System.currentTimeMillis();
            cipherText = encrypt(cipherText, sm4Key, UTF8);
//            if (logger.isDebugEnabled()) {
//                logger.debug("sm4加密耗时：" + (System.currentTimeMillis() - startDate));
//            }
            return cipherText;
        } else {
            return cipherText;
        }

    }

    public static String encrypt(String text, String key, Charset charset) throws Exception {
        if (text != null && !text.isEmpty()) {
            long[] sk = getKey(key, 1);
            return Hex.toHexString(sm4CryptEcb(text.getBytes(charset), sk, 1));
        } else {
            return text;
        }
    }

    public static long[] getKey(String key, int mode) throws Exception {
        if (key == null) {
            throw new Exception("The key is null");
        } else {
            byte[] arrKey;
            if ((arrKey = key.getBytes()).length != 16) {
                throw new Exception("bad key:length is not 16");
            } else {
                long[] sk = new long[32];
                long[] mk = new long[4];
                long[] k = new long[36];
                int i = 0;
                mk[0] = a(arrKey, 0);
                mk[1] = a(arrKey, 4);
                mk[2] = a(arrKey, 8);
                mk[3] = a(arrKey, 12);
                k[0] = mk[0] ^ (long) FK[0];
                k[1] = mk[1] ^ (long) FK[1];
                k[2] = mk[2] ^ (long) FK[2];

                long var11;
                for (k[3] = mk[3] ^ (long) FK[3]; i < 32; ++i) {
                    int var10001 = i + 4;
                    long var10002 = k[i];
                    long var9 = k[i + 1] ^ k[i + 2] ^ k[i + 3] ^ (long) CK[i];
                    byte[] var16 = new byte[4];
                    byte[] var7 = new byte[4];
                    a(var9, var16, 0);
                    var7[0] = a(var16[0]);
                    var7[1] = a(var16[1]);
                    var7[2] = a(var16[2]);
                    var7[3] = a(var16[3]);
                    k[var10001] = var10002 ^ (var11 = a(var7, 0)) ^ a(var11, 13) ^ a(var11, 23);
                    sk[i] = k[i + 4];
                }

                if (mode == 2) {
                    for (i = 0; i < 16; ++i) {
                        var11 = sk[i];
                        sk[i] = sk[31 - i];
                        sk[31 - i] = var11;
                    }
                }

                return sk;
            }
        }
    }

    private static long a(byte[] b, int i) {
        return (long) (b[i] & 255) << 24 | (long) ((b[i + 1] & 255) << 16) | (long) ((b[i + 2] & 255) << 8) | (long) (b[i + 3] & 255);
    }

    private static void a(long n, byte[] b, int i) {
        b[i] = (byte) ((int) (255L & n >> 24));
        b[i + 1] = (byte) ((int) (255L & n >> 16));
        b[i + 2] = (byte) ((int) (255L & n >> 8));
        b[i + 3] = (byte) ((int) (255L & n));
    }

    private static long a(long x, int n) {
        return x << n | x >> 32 - n;
    }

    private static byte a(byte inch) {
        int i = inch & 255;
        return (byte) SBOX_TABLE[i];
    }

    private static void a(long[] sk, byte[] input, byte[] output) {
        int i = 0;
        long[] ulbuf;
        (ulbuf = new long[36])[0] = a(input, 0);
        ulbuf[1] = a(input, 4);
        ulbuf[2] = a(input, 8);

        for (ulbuf[3] = a(input, 12); i < 32; ++i) {
            int var10001 = i + 4;
            long var10003 = ulbuf[i];
            long var10004 = ulbuf[i + 1];
            long var10005 = ulbuf[i + 2];
            long var10006 = ulbuf[i + 3];
            long var15 = sk[i];
            long var13 = var10006;
            long var11 = var10005;
            long var9 = var10004;
            long var7 = var10003;
            long var18 = var9 ^ var11 ^ var13 ^ var15;
            byte[] var6 = new byte[4];
            byte[] var24 = new byte[4];
            a(var18, var6, 0);
            var24[0] = a(var6[0]);
            var24[1] = a(var6[1]);
            var24[2] = a(var6[2]);
            var24[3] = a(var6[3]);
            long var20;
            ulbuf[var10001] = var7 ^ (var20 = a(var24, 0)) ^ a(var20, 2) ^ a(var20, 10) ^ a(var20, 18) ^ a(var20, 24);
        }

        a(ulbuf[35], output, 0);
        a(ulbuf[34], output, 4);
        a(ulbuf[33], output, 8);
        a(ulbuf[32], output, 12);
    }

    private static byte[] a(int mode, byte[] input) {
        if (input == null) {
            return null;
        } else {
            byte[] ret;
            if (mode == 1) {
                int p = 16 - input.length % 16;
                ret = new byte[input.length + p];
                System.arraycopy(input, 0, ret, 0, input.length);

                for (int i = 0; i < p; ++i) {
                    ret[input.length + i] = (byte) p;
                }
            } else {
                int p = input[input.length - 1];
                ret = new byte[input.length - p];
                System.arraycopy(input, 0, ret, 0, input.length - p);
            }

            return ret;
        }
    }

    public static byte[] sm4CryptEcb(byte[] input, long[] key, int mode) throws Exception {
        if (input == null) {
            throw new Exception("input is null!");
        } else {
            if (mode == 1) {
                input = a(1, input);
            }

            int length = input.length;
            ByteArrayInputStream bins = new ByteArrayInputStream(input);

            ByteArrayOutputStream bous;
            byte[] output;
            for (bous = new ByteArrayOutputStream(); length > 0; length -= 16) {
                output = new byte[16];
                byte[] out = new byte[16];
                bins.read(output);
                a(key, output, out);
                bous.write(out);
            }

            output = bous.toByteArray();
            if (mode == 2) {
                output = a(2, output);
            }

            bins.close();
            bous.close();
            return output;
        }
    }

    public static String getHashHex(String input) {
        return getHashHex(input.getBytes(UTF8));
    }

    public static String getHashHex(byte[] hash) {
        SM3Digest digest;
        (digest = new SM3Digest()).update(hash, 0, hash.length);
        hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return Hex.toHexString(hash);
    }

    /**
     * 解密函数
     *
     * @param plainText 需要解密的文本
     * @param sm4Key    sm4key
     * @return
     * @throws Exception
     */
    public static String decodeSm4(String plainText, String sm4Key) throws Exception {
        if (plainText != null && !plainText.isEmpty()) {
            long startDate = System.currentTimeMillis();
            plainText = decrypt(plainText, sm4Key, UTF8);
//            if (logger.isDebugEnabled()) {
//                logger.debug("sm4解密耗时：" + (System.currentTimeMillis() - startDate));
//            }

            return plainText;
        } else {
            return plainText;
        }
    }


    public static String decrypt(String hexCipher, String key, Charset charset) throws Exception {
        if (hexCipher != null && !hexCipher.isEmpty()) {
            long[] sk = getKey(key, 2);
            byte[] b = sm4CryptEcb(Hex.decode(hexCipher), sk, 2);
            return new String(b, charset);
        } else {
            return hexCipher;
        }
    }
}
