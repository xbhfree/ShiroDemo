package com.example.demolisher;

import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShroMD5Test {
    @Test
    public void testMD5(){
        //密码原文
        String password = "123456";
        //md5加密
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println("md5加密 123456 = " + md5Hash.toHex());
        //md5加盐加密
        String salt = "salt";
        Md5Hash md5Hash1 = new Md5Hash(password, salt);
        System.out.println("md5加盐加密 123456 = " + md5Hash1.toHex());
        //md5加盐迭代5次加密
        Md5Hash md5Hash2 = new Md5Hash(password, salt, 5);
        System.out.println("md5加盐迭代5次加密 123456 = " + md5Hash2.toHex());
        //SimpleHash父类加密加盐迭代5次加密
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 5);
        System.out.println("SimpleHash父类加密加盐迭代5次加密 123456 = " + simpleHash.toHex());
        System.out.println(simpleHash.getAlgorithmName());
        System.out.println(simpleHash.getBytes().toString());
        System.out.println(simpleHash.getSalt());
        System.out.println(simpleHash.getIterations());
        System.out.println("-----------------");
        //其他加密
        //Sha1
        SimpleHash simpleHashSha1 = new SimpleHash("Sha1", password, salt, 5);
        System.out.println("Sha1加密加盐迭代5次加密 123456 = " + simpleHashSha1.toHex());
        //Sha256
        SimpleHash simpleHashSha256 = new SimpleHash("Sha256", password, salt, 5);
        System.out.println("Sha256加密加盐迭代5次加密 123456 = " + simpleHashSha256.toHex());
        //Sha384
        SimpleHash simpleHashSha384 = new SimpleHash("Sha384", password, salt, 5);
        System.out.println("Sha384加密加盐迭代5次加密 123456 = " + simpleHashSha384.toHex());
    }
}
