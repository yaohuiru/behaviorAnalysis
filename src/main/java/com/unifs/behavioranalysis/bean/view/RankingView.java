package com.unifs.behavioranalysis.bean.view;

import lombok.Data;

/**
 * @version V1.0
 * @title: RankingView
 * @projectName behavioranalysis
 * @description: 用户发展/营业收入 排行实体封装类
 * @author： 张恭雨
 * @date 2019/6/25 11:37
 */
@Data
public class RankingView {
    private String areaName;
    private String totalAmount;
    private double totalIncome;
}
