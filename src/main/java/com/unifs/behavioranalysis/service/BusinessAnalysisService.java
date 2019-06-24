package com.unifs.behavioranalysis.service;

import com.unifs.behavioranalysis.bean.view.BusinessAnalysisView;

import java.util.List;

/**
 * Classname:BusinessAnalysisService
 * Author:@hushaokai
 * Date:2019/6/21
 * Discription:业务受理总量分析service
 */
public interface BusinessAnalysisService {

//    统计业务数据方法
    List<BusinessAnalysisView>  businessByArea(String orderDate,String areaName);

}
