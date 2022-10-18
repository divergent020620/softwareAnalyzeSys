package com.demo.controller;

import com.demo.entity.Result;
import com.demo.entity.User;
import com.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("hello")
    public String hello(){
        //处理逻辑
        return "hello world!";
    }

    @RequestMapping("login")
    public Object login(@RequestParam("username")String username,
                        @RequestParam("password")String password) throws JsonProcessingException {

        System.out.println("username:"+username);
        User user = userService.selectUser(username, password);

//        return "login succ";

        if(user==null){
            return Result.fail(1,"您输入的用户名或密码错误");
        }else{
            return Result.success("密码正确,进入系统...");
        }
    }

    @RequestMapping("register")
    public Object register(@RequestParam("username")String username,
                        @RequestParam("password")String password)  {

        System.out.println("getting here");
        boolean result= userService.insertUser(username, password);

//        return "login succ";

        if(!result){
            return Result.fail(1,"注册失败");
        }else{
            return Result.success("注册成功,进入系统...");
        }
    }

    @RequestMapping("update")
    public Result updatePW(@RequestParam("username") String username,
                           @RequestParam("oldPassword") String oldPassword,
                           @RequestParam("newPassword")String newPassword) throws JsonProcessingException {

        /*
            先查询用户输入的旧密码是否正确,如果不正确直接返回
            如果正确,进行密码更改的操作
         */
        User user = userService.selectUser(username, oldPassword);
        if(user==null){
            //说明没找到 那就直接返回错误
            return Result.fail(1,"您输入的用户名或密码错误");
        }

        //更改密码
        userService.updatePassword(username, newPassword);


        return Result.success("更改密码成功");
    }

    @RequestMapping("insert")
    public Result insertUser(@RequestParam("username")String username,
                             @RequestParam("password")String password) throws JsonProcessingException {

        boolean b = userService.insertUser(username, password);

        if(b==true){
            return Result.success("插入用户成功");
        }else{
            return Result.fail(1,"插入用户失败");
        }

    }


}
