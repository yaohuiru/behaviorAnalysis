package com.unifs.behavioranalysis.base;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @version V1.0
 * @title: LogEntity
 * @projectName ExcetptionAndValid
 * @description: TODO
 * @author： 张恭雨
 * @date 2019/4/29 11:54
 */

@Data //注解，自动配置getter setter方法
@ToString //注解，自动重新toString方法
public class LogEntity {
    private String username;    //来访者姓名
    private String ip;          //来访者IP地址
    private String args;        //参数
    private String interfacePath; //接口路径
    private String status;      //访问结果状态
    private String type;        //操作类型
    private String detail;      //详细
    private Date time;          //访问时间
    private String content;     //内容

}
