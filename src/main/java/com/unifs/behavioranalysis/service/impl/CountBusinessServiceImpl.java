package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.bean.view.DevCountView;
import com.unifs.behavioranalysis.bean.view.OrderAmountView;
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
    public DevCountView countUserDevelop(String orderDate, String code) {
       /* List<ProductInfo> list = productInfoMapper.selectByProvinceCode(code);
        if (!list.isEmpty()){
            System.out.println(list.size());
        }*/

        return productOrderMapper.selectUserCountByMonth(code,orderDate);

    }

    @Override
    public List<OrderAmountView> countOrderAmount() {
        return productOrderMapper.selectOrderAmount();
    }


}
