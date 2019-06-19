package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.ProductOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.List;
@Component
public interface ProductOrderMapper {
    int deleteByPrimaryKey(String userNo);

    int insert(ProductOrder record);

    ProductOrder selectByPrimaryKey(String userNo);

    List<ProductOrder> selectAll();

    int updateByPrimaryKey(ProductOrder record);

    int selectUserCountByMonth(@Param("areaName") String areaName, @Param("orderDate") String orderDate);
}