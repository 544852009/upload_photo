package com.my.photo.uploadphoto.controller;

import com.my.photo.uploadphoto.Exception.UserResult;
import com.my.photo.uploadphoto.service.UserInfoService;
import com.my.photo.uploadphoto.util.GetRequestIp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    private Logger logs = LoggerFactory.getLogger(LoginController.class);


    @PostMapping("/register")
    @ResponseBody
    public UserResult register(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        String passWordAgain = request.getParameter("passWordAgain");

        userInfoService.saveUserInfo(userName,passWord,passWordAgain);
        return UserResult.success("注册成功");
    }


    @PostMapping("/login")
    @ResponseBody
    public UserResult login(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        userInfoService.login(request,userName,passWord);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logs.error(simpleDateFormat.format(new Date())+"登录用户："+userName+"[IP:"+ GetRequestIp.getIpAddress(request) +"]");
        return UserResult.success("登录成功");
    }

}
