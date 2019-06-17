package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.ProductOrder;
import java.util.List;

public interface ProductOrderMapper {
    int deleteByPrimaryKey(String userNo);

    int insert(ProductOrder record);

    ProductOrder selectByPrimaryKey(String userNo);

    List<ProductOrder> selectAll();

    int updateByPrimaryKey(ProductOrder record);
}