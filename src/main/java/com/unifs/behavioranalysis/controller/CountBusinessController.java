package com.unifs.behavioranalysis.controller;

import com.unifs.behavioranalysis.bean.view.DevCountView;
import com.unifs.behavioranalysis.service.CountBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountBusinessController {
    @Autowired
    CountBusinessService countBusinessService;
    @RequestMapping(value = "/countUserDevelop" ,method = RequestMethod.POST)
    @ResponseBody
    public DevCountView countUserDevelop(@RequestParam(value = "orderDate",required=false )String orderDate ,@RequestParam(value = "areaName",required = false)String areaName){
        System.out.println(orderDate+areaName);
        DevCountView view =  countBusinessService.countUserDevelop(orderDate,areaName);
        System.out.println(view.toString());
        return view;
    }


}
