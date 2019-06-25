package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.bean.view.DevCountView;
import com.unifs.behavioranalysis.bean.view.OrderAmountView;
import com.unifs.behavioranalysis.bean.view.RankingView;
import com.unifs.behavioranalysis.dao.ProductInfoMapper;
import com.unifs.behavioranalysis.dao.ProductOrderMapper;
import com.unifs.behavioranalysis.service.CountBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountBusinessServiceImpl implements CountBusinessService {

    @Autowired
    ProductOrderMapper productOrderMapper;
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public DevCountView countUserDevelop(String orderDate, String code) {
        if (code.equals("all")) {
            return productOrderMapper.selectAllCount(orderDate);
        } else {
            return productOrderMapper.selectUserCountByMonth(code, orderDate);
        }
    }

    @Override
    public List<OrderAmountView> countOrderAmount() {
        return productOrderMapper.selectOrderAmount();
    }

    @Override
    public List<RankingView> rankingInfo(String areaName, String type) throws Exception {
        //集合声明
        List<RankingView> views=new ArrayList<>();
        //判断获取排行类型。
        if ("develop".equals(type)) {
            views=productOrderMapper.selectUserRankingByName(areaName);
        }
        if ("income".equals(type)){
            views=productOrderMapper.selectIncomeRankingByName(areaName);
        }
        return views;
    }


}
