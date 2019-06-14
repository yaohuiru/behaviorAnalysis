package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.base.LogEntity;
import com.unifs.behavioranalysis.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version V1.0
 * @title: LogServiceImpl
 * @projectName ExcetptionAndValid
 * @description: TODO
 * @author： 张恭雨
 * @date 2019/4/29 12:01
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Override
    public void saveLog(LogEntity logEntity){
        System.out.println(logEntity.getArgs());
        System.out.println(logEntity.getContent());
        System.out.println(logEntity.getDetail());
        System.out.println(logEntity.getInterfacePath());
        System.out.println(logEntity.getIp());
        System.out.println(logEntity.getTime());
        System.out.println(logEntity.getType());
        System.out.println(logEntity.getStatus());

    }
}
