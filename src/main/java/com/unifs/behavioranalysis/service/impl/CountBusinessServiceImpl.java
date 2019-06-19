package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.bean.ProductOrder;
import com.unifs.behavioranalysis.dao.ProductInfoMapper;
import com.unifs.behavioranalysis.dao.ProductOrderMapper;
import com.unifs.behavioranalysis.service.CountBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountBusinessServiceImpl implements CountBusinessService {

    @Autowired
    ProductOrderMapper productOrderMapper;
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public int countUserDevelop(String orderDate, String code) {
       /* List<ProductInfo> list = productInfoMapper.selectByProvinceCode(code);
        if (!list.isEmpty()){
            System.out.println(list.size());
        }*/

        return productOrderMapper.selectUserCountByMonth(code,orderDate);

    }
}