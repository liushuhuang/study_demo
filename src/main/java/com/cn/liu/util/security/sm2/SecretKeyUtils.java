package com.cn.liu.util.security.sm2;


import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class SecretKeyUtils {

    /**
     * SM2算法生成密钥对
     * @return 密钥对信息
     */
    public static KeyPair generateSm2KeyPair() throws Exception {
        try {
            final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
            // 获取一个椭圆曲线类型的密钥对生成器
            final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
            SecureRandom random = new SecureRandom();
            // 使用SM2的算法区域初始化密钥生成器
            kpg.initialize(sm2Spec, random);
            // 获取密钥对
            KeyPair keyPair = kpg.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            System.err.println(e);
            throw new Exception(e);
        }
    }

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
}
