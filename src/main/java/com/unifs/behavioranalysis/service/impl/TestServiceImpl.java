package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.annotation.ServiceLog;
import com.unifs.behavioranalysis.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @version V1.0
 * @title: TestServiceImpl
 * @projectName micromall
 * @description: TODO
 * @author： 张恭雨
 * @date 2019/5/9 15:14
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    @ServiceLog(context = "xxxx01")
    public void test01() {
        System.out.println("xxxxx");
    }
}
