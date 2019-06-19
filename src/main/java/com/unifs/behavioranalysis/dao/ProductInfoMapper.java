package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.ProductInfo;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ProductInfoMapper {
    int deleteByPrimaryKey(String productId);

    int insert(ProductInfo record);

    ProductInfo selectByPrimaryKey(String productId);

    List<ProductInfo> selectAll();

    int updateByPrimaryKey(ProductInfo record);
}