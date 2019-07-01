package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.ProductOrder;
import com.unifs.behavioranalysis.bean.view.BusinessAnalysisView;
import com.unifs.behavioranalysis.bean.view.DevCountView;
import com.unifs.behavioranalysis.bean.view.OrderAmountView;
import com.unifs.behavioranalysis.bean.view.RankingView;
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

    DevCountView selectUserCountByMonth(@Param("areaName") String areaName, @Param("orderDate") String orderDate);

    DevCountView selectAllCount(@Param("orderDate") String orderDate);

    List<OrderAmountView> selectOrderAmount();

    List<BusinessAnalysisView> businessByArea(@Param("orderDate")String orderDate,@Param("areaName")String areaName);

    //查询用户用户发展排行信息
    List<RankingView> selectUserRankingByName(String areaName);
    //查询营业收入排行
    List<RankingView> selectIncomeRankingByName(String areaName);
}