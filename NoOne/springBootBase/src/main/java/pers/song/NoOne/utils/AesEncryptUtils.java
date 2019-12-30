package pers.song.NoOne.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * 前后端数据传输加密工具类
 * @author pibigstar
 *
 */
public class AesEncryptUtils {
    //可配置到Constant中，并读取配置文件注入
    private static final String KEY = "1234567812345678";
    private static final String IV = "1234567812345678";

    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES/CBC/NoPadding";

    /**
     * 加密方法 返回base64加密字符串
     * 和前端保持一致
     * @param data  要加密的数据
     * @param key 加密key
     * @param iv 加密iv
     * @return 加密的结果
     * @throws Exception
     */
    public static String encrypt(String data, String key, String iv) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);//"算法/模式/补码方式"NoPadding PKCS5Padding
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new Base64().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密方法
     * @param data 要解密的数据
     * @param key  解密key
     * @param iv 解密iv
     * @return 解密的结果
     * @throws Exception
     */
    public static String decrypt(String data, String key, String iv) throws Exception {
        try {
//        byte[] encrypted1 = new Base64().decode(data);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(new Base64().decode(data));
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String content) throws Exception {
        return encrypt(content, KEY,IV);
    }
    public static String decrypt(String encryptStr) throws Exception {
        return decrypt(encryptStr, KEY,IV);
    }


    public static void main(String[] args) throws Exception {
        String content = "{\"info\":{\"usercode\":\"\",\"token\":\"\"},\"data\":{\"username\":\"111\",\"password\":\"bcbe3365e6ac95ea2c0343a2395834dd\"}}";
        System.out.println("加密前：" + content);

        String encrypt = encrypt(content, KEY,IV);
        System.out.println("加密后：" + encrypt);

        String decrypt = decrypt(encrypt, KEY,IV);
        System.out.println("解密后：" + decrypt);
    }
}
