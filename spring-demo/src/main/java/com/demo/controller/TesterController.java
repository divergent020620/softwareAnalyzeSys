package com.demo.controller;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

import com.demo.entity.Result;
import com.demo.service.HistoryService;
import com.demo.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@ResponseBody
public class TesterController {


    @Autowired
    private TesterService testerService;

    @Autowired
    private HistoryService historyService;


//    @RequestMapping("analyze")
//    //public String selectPass(){
//    public Result analyze(@RequestBody HashMap map)  {
//
//        String testResult = null;
//        try {
//            testResult = testerService.analyze(map);
//            return Result.success(testResult);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @RequestMapping("analyze")
//    //public String selectPass(){
//    public Result analyze(@RequestBody Double[] attrs)  {
//
//        String testResult = null;
//        try {
//            testResult = testerService.analyze(attrs);
//            return Result.success(testResult);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }


    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @CrossOrigin
    public Result upload(@RequestParam("fileObj") MultipartFile formData,
                         @RequestParam("selected") String type,
                         @RequestParam("username")String username) {
        System.out.println("TYPE: "+type);
        System.out.println("Getting in ");
        String result=null;
        try {
            if (formData.isEmpty()) {
                return Result.fail(1, "上传文件不能为空");
            }
            //分析前端传来的文件流 相关逻辑

            result = testerService.analyze(formData,type);

            String isBug=null;
            if ("0".equals(result))
                isBug="clean";
            if ("1".equals(result))
                isBug="buggy";

            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String datetime = dateFormat.format(date);

            historyService.insert(
                    username,
                    formData.getOriginalFilename(),
                    type,
                    datetime,
                    isBug);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //返回预测结果
        return Result.success(result);
    }

}
