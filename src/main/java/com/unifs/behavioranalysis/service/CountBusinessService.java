package com.unifs.behavioranalysis.service;

import com.unifs.behavioranalysis.bean.view.DevCountView;

public interface CountBusinessService {
    //用户发展量
    DevCountView countUserDevelop(String OrderDate,String code );
}
