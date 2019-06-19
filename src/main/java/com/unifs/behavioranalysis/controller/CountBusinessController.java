package com.unifs.behavioranalysis.controller;

import com.unifs.behavioranalysis.bean.view.DevCountView;
import com.unifs.behavioranalysis.service.CountBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountBusinessController {
    @Autowired
    CountBusinessService countBusinessService;
    @RequestMapping(value = "/countUserDevelop" ,method = RequestMethod.POST)
    public DevCountView countUserDevelop(String orderDate){
        DevCountView view =  countBusinessService.countUserDevelop("201906","北京市");
        System.out.println(view.toString());
        return view;
    }


}
