package com.unifs.behavioranalysis;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BehaviorAnalysisApplicationTests {
    @Autowired
    StringEncryptor encryptor;

    @Test
    public void contextLoads() {
    }

    //加密
    @Test
    public void testBsslocal() {
        String url = encryptor.encrypt("jdbc:mysql://192.168.1.193:3306/behaviors");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("123456");
        System.out.println("url:" + url);
        System.out.println("用户名:" + name);
        System.out.println("密码:" + password);
    }

}
