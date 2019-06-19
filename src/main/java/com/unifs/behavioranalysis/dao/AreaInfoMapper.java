package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.AreaInfo;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AreaInfoMapper {
    int deleteByPrimaryKey(String areaId);

    int insert(AreaInfo record);

    AreaInfo selectByPrimaryKey(String areaId);

    List<AreaInfo> selectAll();

    int updateByPrimaryKey(AreaInfo record);

    List<AreaInfo> selectByParentId(String parentId);

    List<String> selectAreaNameByParentId(String parentId);
}