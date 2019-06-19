package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.dao.ProductOrderMapper;
import com.unifs.behavioranalysis.service.CountBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountBusinessServiceImpl implements CountBusinessService {

    @Autowired
    ProductOrderMapper productOrderMapper;
    @Override
    public int countUserDevelop(String orderDate) {
        return productOrderMapper.selectUserConuntByMonth(orderDate);
    }
}
