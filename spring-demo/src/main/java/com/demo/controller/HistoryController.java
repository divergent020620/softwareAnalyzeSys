package com.demo.controller;

import com.demo.entity.History;
import com.demo.entity.Result;
import com.demo.service.HistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;


    @RequestMapping("selectByID")
    public Result selectByID(@RequestParam("username")String username) throws JsonProcessingException {

        System.out.println("finding history of "+username);
        List<History> historyList = historyService.selectByID(username);

//        return "login succ";

//        History[] historyArr = (History[]) historyList.toArray();

        if(historyList.size()==0){
            return Result.fail(0,"无记录");
        }else{
            return Result.success(historyList);
        }
    }

    @RequestMapping("insert")
    public Result insert(@RequestParam("username")String username,
                         @RequestParam("fileName")String fileName,
                         @RequestParam("modelType")String modelType,
                         @RequestParam("time")String time,
                         @RequestParam("isBug")String isBug) {


        boolean result = historyService.insert(username,fileName,modelType,time,isBug);

//        return "login succ";

        if(!result){
            return Result.fail(0,"生成历史记录失败");
        }else{
            return Result.success("ok");
        }
    }



}
