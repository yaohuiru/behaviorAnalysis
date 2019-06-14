package com.unifs.behavioranalysis.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @version V1.0
 * @title: User
 * @projectName ExcetptionAndValid
 * @description: TODO
 * @author： 张恭雨
 * @date 2019/4/25 18:03
 */
@Data //注解，自动配置getter setter方法
@ToString //注解，自动重新toString方法
@ApiModel(value = "User对象") //API接口注释
public class User {
    @NotNull
    @ApiModelProperty(value = "username",required = true)
    private String username;
    @NotNull
    @ApiModelProperty(value = "password",required = true)
    private String password;
    @Max(150)
    @Min(0)
    @ApiModelProperty(value = "age",required = true)
    private String age;
}
