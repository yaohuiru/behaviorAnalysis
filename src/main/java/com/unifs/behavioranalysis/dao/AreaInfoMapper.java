package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.AreaInfo;
import java.util.List;

public interface AreaInfoMapper {
    int deleteByPrimaryKey(Integer indexId);

    int insert(AreaInfo record);

    AreaInfo selectByPrimaryKey(Integer indexId);

    List<AreaInfo> selectAll();

    int updateByPrimaryKey(AreaInfo record);
}