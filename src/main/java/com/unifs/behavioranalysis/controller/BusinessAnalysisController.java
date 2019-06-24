package com.unifs.behavioranalysis.controller;

import com.unifs.behavioranalysis.bean.view.BusinessAnalysisView;
import com.unifs.behavioranalysis.service.BusinessAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Classname:BusinessAnalysisController
 * Author:@hushaokai
 * Date:2019/6/21
 * Discription:业务受理量分析controller
 */
@RestController
public class BusinessAnalysisController {

    @Autowired
    private BusinessAnalysisService businessAnalysisService;

    @RequestMapping(value = "/businessAnalysis",method = RequestMethod.POST)
    public List<BusinessAnalysisView> businessAnalysis(@RequestParam(value = "orderDate",required = false)String orderdate,@RequestParam(value = "areaName",required = false)String  areaName){

        System.out.println(orderdate+areaName);
        List businessAnalysis=businessAnalysisService.businessByArea(orderdate,areaName);
        System.out.println(businessAnalysis.toString());
        return businessAnalysis;


    }

}
