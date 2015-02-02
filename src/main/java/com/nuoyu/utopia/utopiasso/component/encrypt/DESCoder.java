package com.nuoyu.utopia.utopiasso.component.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.math.BigInteger;
import java.security.Key;
import java.security.SecureRandom;

/**
 * Created by liuxin3 on 2015/1/30.
 * DES加密 对称算法  支付加密解密 可以扩展其他加密算法如AES、Blowfish等
 * 如需扩展其他对称算法 改写toKey方法
 */
public class DESCoder extends Coder {

    public static final String ALGORITHM = "DES";

    /**
     * 生成密钥 同一入参生成的key一致
     *
     * @param seed
     * @return
     * @throws Exception
     */
    public static String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;
        if (seed != null) {
            secureRandom = new SecureRandom(decryptBASE64(seed));
        } else {
            secureRandom = new SecureRandom();
        }
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(secureRandom);
        SecretKey secretKey = kg.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * 生成密钥 每次调用生成不同的key
     *
     * @return
     * @throws Exception
     */
    public static String initKey() throws Exception {
        return initKey(null);
    }

    /**
     * 转换密钥<br>
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(dks);
        // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
        // SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        return secretKey;
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        String testStr = "我是一个小乌龟";
        String key = initKey(testStr);
        String testSource = "跑呀跑不快";
        byte[] temp = encrypt(testSource.getBytes(), key);
        System.out.println(new BigInteger(temp).toString());
        System.out.println(new BigInteger("-123625515089743250762512920140115791468").toString());
        System.out.println(new String(decrypt(new BigInteger("-123625515089743250762512920140115791468").toByteArray(),key)));
    }
}
