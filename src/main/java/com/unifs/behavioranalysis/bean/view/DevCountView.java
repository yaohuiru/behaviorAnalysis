package com.unifs.behavioranalysis.bean.view;

import lombok.Data;
import lombok.ToString;

/**
 * @version V1.0
 * @title: DevCountView
 * @projectName behavioranalysis
 * @description: 业务发展统计视图
 * @author： 张恭雨
 * @date 2019/6/19 10:57
 */
@Data
@ToString
public class DevCountView {
    private int devAmount;  //用户发展量
    private int acceptAmount;   //业务受理量
    private double dayTurnover; //日均营业额
    private double totalTurnover;   //总营业额
}
