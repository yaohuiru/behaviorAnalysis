package com.unifs.behavioranalysis.service;

import com.unifs.behavioranalysis.bean.view.DevCountView;
import com.unifs.behavioranalysis.bean.view.OrderAmountView;

import java.util.List;

public interface CountBusinessService {
    //用户发展量
    DevCountView countUserDevelop(String OrderDate, String code);

    //营业额
    List<OrderAmountView> countOrderAmount();
}
