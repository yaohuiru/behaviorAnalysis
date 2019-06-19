package com.unifs.behavioranalysis.controller;

import com.unifs.behavioranalysis.annotation.ControlLog;
import com.unifs.behavioranalysis.base.Resp;
import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.enums.RespCode;
import com.unifs.behavioranalysis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @version V1.0
 * @title: TestController
 * @projectName ExcetptionAndValid
 * @description: TODO
 * @author： 张恭雨
 * @date 2019/4/25 18:05
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @ControlLog(operateType = "test", context = "AOP日志功能测试")
    @RequestMapping(value = "/test" ,method = RequestMethod.POST)
    public Resp test(@Validated @RequestBody User user) {
        Resp resp = new Resp(RespCode.SUCCESS);
        //testService.test01();
        return resp;

    }






}
