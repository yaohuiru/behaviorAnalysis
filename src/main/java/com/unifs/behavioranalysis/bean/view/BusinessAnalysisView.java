package com.unifs.behavioranalysis.bean.view;

import lombok.Data;
import lombok.ToString;

/**
 * Classname:BusinessAnalysisView
 * Author:@hushaokai
 * Date:2019/6/21
 * Discription:TODO
 */
@Data
@ToString
public class BusinessAnalysisView {
    //业务名称
    private String productName;
    //业务受理数量
    private String productAmount;
}
