package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.ProductOrder;
import com.unifs.behavioranalysis.bean.view.DevCountView;
import com.unifs.behavioranalysis.bean.view.OrderAmountView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ProductOrderMapper {
    int deleteByPrimaryKey(String userNo);

    int insert(ProductOrder record);

    ProductOrder selectByPrimaryKey(String userNo);

    List<ProductOrder> selectAll();

    int updateByPrimaryKey(ProductOrder record);

    DevCountView selectUserCountByMonth(@Param("areaName") String areaName , @Param("orderDate") String orderDate);

    List<OrderAmountView> selectOrderAmount();
}