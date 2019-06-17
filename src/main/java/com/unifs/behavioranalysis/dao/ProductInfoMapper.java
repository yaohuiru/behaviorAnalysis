package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.ProductInfo;
import java.util.List;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(String productId);

    int insert(ProductInfo record);

    ProductInfo selectByPrimaryKey(String productId);

    List<ProductInfo> selectAll();

    int updateByPrimaryKey(ProductInfo record);
}