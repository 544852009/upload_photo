package com.my.photo.uploadphoto.controller;

import com.alibaba.fastjson.JSON;
import com.my.photo.uploadphoto.vo.Ztree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HopController {



    @GetMapping(value = {"/","/login"})
    public String login(){

        return "login";
    }

    @GetMapping(value = {"/ztree"})
    public String ztree(){

        List<String> ztreeList = new ArrayList<>();
        Ztree ztree = new Ztree();
        ztree.setId(1);
        ztree.setName("one");
        ztree.setPid(0);
        ztreeList.add(ztree.toString());
        Ztree ztree2 = new Ztree();
        ztree2.setId(2);
        ztree2.setName("two");
        ztree2.setPid(1);
        ztreeList.add(ztree2.toString());
        System.out.println(ztree.toString());
        String jsonString = JSON.toJSONString(ztreeList).replace("\"","");
        System.out.println(jsonString);

        return "ztree";
    }

    @GetMapping("/register")
    public String register(){

        return "register";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request ){
        Object userInfo = request.getSession().getAttribute("userInfo");
        if(userInfo != null){
            request.getSession().removeAttribute("userInfo");
        }
        return "/";
    }

    @GetMapping("/index/upFile")
    public String upFile(){
        return "upfile";
    }

    @GetMapping("/index/addPhoto")
    public String addPhoto(){
        return "add-photo";
    }

    @GetMapping("/index/addPhotos")
    public String addPhotos(){
        return "add-photos";
    }


}
