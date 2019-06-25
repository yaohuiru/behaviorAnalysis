package com.unifs.behavioranalysis.service;

import com.unifs.behavioranalysis.bean.view.DevCountView;
import com.unifs.behavioranalysis.bean.view.OrderAmountView;
import com.unifs.behavioranalysis.bean.view.RankingView;

import java.util.List;

public interface CountBusinessService {
    //用户发展量
    DevCountView countUserDevelop(String OrderDate, String code);

    //营业额
    List<OrderAmountView> countOrderAmount();

    //获取排行信息
    List<RankingView> rankingInfo(String areaName,String type)throws Exception;
}
