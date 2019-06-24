package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.bean.ProductInfo;
import com.unifs.behavioranalysis.bean.view.BusinessAnalysisView;
import com.unifs.behavioranalysis.dao.ProductInfoMapper;
import com.unifs.behavioranalysis.dao.ProductOrderMapper;
import com.unifs.behavioranalysis.service.BusinessAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classname:BusinessAnalysisServiceImpl
 * Author:@hushaokai
 * Date:2019/6/21
 * Discription:业务统计量分析的具体实现类
 */
@Service
public class BusinessAnalysisServiceImpl implements BusinessAnalysisService {
    @Autowired
    ProductOrderMapper productOrderMapper;

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<BusinessAnalysisView> businessByArea(String orderDate, String areaName) {
        return productOrderMapper.businessByArea(orderDate,areaName);
    }
}
