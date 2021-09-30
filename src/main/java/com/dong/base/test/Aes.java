package com.dong.base.test;


import com.dong.base.util.ParseSystemUtil;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/***
 * 高级加密标准（英语：Advanced Encryption Standard，缩写：AES），是一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。
 *
 * 那么为什么原来的DES会被取代呢，，原因就在于其使用56位密钥，比较容易被破解。而AES可以使用128、192、和256位密钥，并且用128位分组加密和解密数据，
 * 相对来说安全很多。完善的加密算法在理论上是无法破解的，除非使用穷尽法。使用穷尽法破解密钥长度在128位以上的加密数据是不现实的，仅存在理论上的可能性。
 * 统计显示，即使使用目前世界上运算速度最快的计算机，穷尽128位密钥也要花上几十亿年的时间，更不用说去破解采用256位密钥长度的AES算法了。
 */
public class Aes {

    public static void main(String args[]){
       test2();

    }

    public static void test1(){
        String content = "美女，约吗？";
        String password = "123";
        System.out.println("加密之前：" + content);

        // 加密
        byte[] encrypt = Aes.encrypt(content, password);
        System.out.println("加密后的内容：" + new String(encrypt));

        // 解密
        byte[] decrypt = Aes.decrypt(encrypt, password);
        System.out.println("解密后的内容：" + new String(decrypt));
    }
    public static void test2() {
        String content = "美女，约吗？";
        String password = "123";
        System.out.println("加密之前：" + content);
        // 加密
        byte[] encrypt = Aes.encrypt(content, password);
        System.out.println("加密后的内容：" + new String(encrypt));

        //如果想要加密内容不显示乱码，可以先将密文转换为16进制
        String hexStrResult = ParseSystemUtil.parseByte2HexStr(encrypt);
        System.out.println("16进制的密文："  + hexStrResult);

        //如果的到的是16进制密文，别忘了先转为2进制再解密
        byte[] twoStrResult = ParseSystemUtil.parseHexStr2Byte(hexStrResult);

        // 解密
        byte[] decrypt = Aes.decrypt(twoStrResult, password);
        System.out.println("解密后的内容：" + new String(decrypt));
    }

    /**
     * AES加密字符串
     *
     * @param content
     *            需要被加密的字符串
     * @param password
     *            加密需要的密码
     * @return 密文
     */
    public static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者

            kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
            // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            // null。

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES");// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return result;

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密AES加密过的字符串
     *
     * @param content
     *            AES加密过过的内容
     * @param password
     *            加密时的密码
     * @return 明文
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] result = cipher.doFinal(content);
            return result; // 明文

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }



}
