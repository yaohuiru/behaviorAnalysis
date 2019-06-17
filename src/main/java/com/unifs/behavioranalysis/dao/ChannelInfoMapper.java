package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.ChannelInfo;
import java.util.List;

public interface ChannelInfoMapper {
    int deleteByPrimaryKey(String channelId);

    int insert(ChannelInfo record);

    ChannelInfo selectByPrimaryKey(String channelId);

    List<ChannelInfo> selectAll();

    int updateByPrimaryKey(ChannelInfo record);
}