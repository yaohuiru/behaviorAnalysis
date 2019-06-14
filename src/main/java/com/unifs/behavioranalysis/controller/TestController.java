package com.unifs.behavioranalysis.controller;

import com.unifs.behavioranalysis.annotation.ControlLog;
import com.unifs.behavioranalysis.base.Resp;
import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.enums.RespCode;
import com.unifs.behavioranalysis.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @title: TestController
 * @projectName ExcetptionAndValid
 * @description: TODO
 * @author： 张恭雨
 * @date 2019/4/25 18:05
 */
@RestController
@Api(value = "TestController", description = "测试控制层")    //swagger2生成API文档注解
public class TestController {
    @Autowired
    private TestService testService;

    @ControlLog(operateType = "test", context = "AOP日志功能测试")
    @RequestMapping(value = "/test" ,method = RequestMethod.POST)
    @ApiOperation(value = "根据用户编号获取用户姓名", notes = "test: 仅1和2有正确返回")
    public Resp test(@Validated @RequestBody User user) {
        Resp resp = new Resp(RespCode.SUCCESS);
        //testService.test01();
        return resp;

    }
}
