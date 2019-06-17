package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.AreaInfo;
import java.util.List;

public interface AreaInfoMapper {
    int deleteByPrimaryKey(Integer areaId);

    int insert(AreaInfo record);

    AreaInfo selectByPrimaryKey(Integer areaId);

    List<AreaInfo> selectAll();

    int updateByPrimaryKey(AreaInfo record);
}