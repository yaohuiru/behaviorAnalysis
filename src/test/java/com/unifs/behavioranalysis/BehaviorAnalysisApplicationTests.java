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
        String url = encryptor.encrypt("jdbc:oracle:thin:@130.52.160.206:1521:bsslocal");
        String name = encryptor.encrypt("JF_CCJ");
        String password = encryptor.encrypt("Abcd1234");
        System.out.println("url:" + url);
        System.out.println("用户名:" + name);
        System.out.println("密码:" + password);
    }

}
