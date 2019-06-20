package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.bean.view.DevCountView;
import com.unifs.behavioranalysis.dao.ProductInfoMapper;
import com.unifs.behavioranalysis.dao.ProductOrderMapper;
import com.unifs.behavioranalysis.service.CountBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountBusinessServiceImpl implements CountBusinessService {

    @Autowired
    ProductOrderMapper productOrderMapper;
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public DevCountView countUserDevelop(String orderDate, String code) {
        if (code.equals("all")){
            return productOrderMapper.selectAllCount(orderDate);
        }else {
            return productOrderMapper.selectUserCountByMonth(code,orderDate);
        }
    }
}
