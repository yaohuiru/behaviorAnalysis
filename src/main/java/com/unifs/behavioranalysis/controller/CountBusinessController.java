package com.unifs.behavioranalysis.controller;

import com.unifs.behavioranalysis.base.Resp;
import com.unifs.behavioranalysis.bean.view.DevCountView;
import com.unifs.behavioranalysis.bean.view.OrderAmountView;
import com.unifs.behavioranalysis.bean.view.RankingView;
import com.unifs.behavioranalysis.enums.RespCode;
import com.unifs.behavioranalysis.service.CountBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    @RequestMapping(value = "countOrderAmount", method = RequestMethod.GET)
    public List<OrderAmountView> countOrderAmount() {
        List<OrderAmountView> rawOrderAmount = countBusinessService.countOrderAmount();
        System.out.println(rawOrderAmount);
        return rawOrderAmount;
    }
    
    /**
     　　* @description: 获取排行信息
     　　* @param ${tags}
     　　* @return ${return_type}
     　　* @throws
     　　* @author 张恭雨
     　　* @date 2019/6/25 11:46
     　　*/
    @RequestMapping(value = "/ranking",method = RequestMethod.POST)
    public Resp rankingInfo(@NotNull String areaName, @NotNull String type)throws Exception{
        List<RankingView> views=countBusinessService.rankingInfo(areaName,type);
        Resp resp=new Resp(RespCode.SUCCESS);
        resp.setData(views);
        return resp;
    }

}
