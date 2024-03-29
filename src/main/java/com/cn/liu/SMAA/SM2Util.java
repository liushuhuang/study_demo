package com.cn.liu.SMAA;

import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class SM2Util {

    private static Map<String, String> d = new HashMap();
    public static void generatePaKey() {
        try {
            X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
            ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN());
            ECKeyPairGenerator keyPairGenerator;
            (keyPairGenerator = new ECKeyPairGenerator()).init(new ECKeyGenerationParameters(domainParameters, SecureRandom.getInstance("SHA1PRNG")));
            AsymmetricCipherKeyPair asymmetricCipherKeyPair;
            String privateKeyHex = ((ECPrivateKeyParameters)(asymmetricCipherKeyPair = keyPairGenerator.generateKeyPair()).getPrivate()).getD().toString(16);
            System.out.println("priKey:" + privateKeyHex);
            String publicKeyHex = Hex.toHexString(((ECPublicKeyParameters)asymmetricCipherKeyPair.getPublic()).getQ().getEncoded(false));
            System.out.println("pubKey:" + publicKeyHex);
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }
    public static String decodeSm2Cipher(String pubSm2Key, String cipherText) throws Exception {
        String priKey;
        if ((priKey = (String) d.get(pubSm2Key)) == null) {
            throw new Exception();//AresRuntimeException("ESM90", new Object[]{"未找到对应的私钥:" + pubSm2Key});
        } else {
            try {
                return decryptData(priKey, cipherText);
            } catch (Exception var3) {
                //logger.error("sm2解密异常:" + var3.toString(), var3);
                //throw new AresRuntimeException("ESM91", new Object[]{"sm2解密异常"});
                throw new Exception();
            }
        }
    }

    /**
     * 解密函数
     *
     * @param privateKey sm2私钥
     * @param data       需要解密的数据
     * @return
     * @throws Exception
     */
    public static String decryptData(String privateKey, String data) throws Exception {
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN());
        SM2Engine sm2Engine = new SM2Engine();
        BigInteger priKey = new BigInteger(privateKey, 16);
        ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(priKey, domainParameters);
        sm2Engine.init(false, privateKeyParameters);
        byte[] d = Hex.decode(data);
        return new String(sm2Engine.processBlock(d, 0, d.length));
    }


    /**
     * 加密函数
     *
     * @param publicKey sm2公钥
     * @param data      需要加密的数据
     * @return
     * @throws Exception
     */
    public static String encryptData(String publicKey, String data) throws Exception {
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN());
        SM2Engine sm2Engine = new SM2Engine();
        System.out.println("Hex.decode(publicKey):" + Hex.decode(publicKey));
        ECPoint publicKeyPoint = sm2ECParameters.getCurve().decodePoint(Hex.decode(publicKey));
        //ECPoint publicKeyPoint = sm2ECParameters.getCurve().decodePoint(StrUtil.bytes(publicKey));
        ECPublicKeyParameters pubKeyParam = new ECPublicKeyParameters(publicKeyPoint, domainParameters);
        sm2Engine.init(true, new ParametersWithRandom(pubKeyParam));
        byte[] d = data.getBytes();
        return Hex.toHexString(sm2Engine.processBlock(d, 0, d.length));
    }


}
