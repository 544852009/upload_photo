package com.my.photo.uploadphoto.service;

import com.my.photo.uploadphoto.Exception.UserException;
import com.my.photo.uploadphoto.dao.UserInfoDao;
import com.my.photo.uploadphoto.entity.UserInfo;
import com.my.photo.uploadphoto.util.IDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao ;

    public void saveUserInfo(String userName,String passWord,String passwordAgain){

        if(StringUtils.isEmpty(userName)){
            throw new UserException("邮箱不能为空哦！");
        }
        if(StringUtils.isEmpty(passWord)){
            throw new UserException("密码不能为空哦！");
        }
        if(!passWord.equals(passwordAgain)){
            throw new UserException("两次密码不一致！");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setId(IDUtil.getLongId());
        userInfo.setUserName(userName);
        userInfo.setPassWord(DigestUtils.md5DigestAsHex(passWord.getBytes()));
        userInfoDao.save(userInfo);
    }

    public void login(HttpServletRequest request , String userName, String passWord) {

        if(StringUtils.isEmpty(userName)){
            throw new UserException("邮箱不能为空哦！");
        }
        if(!passWord.equals(passWord)){
            throw new UserException("密码不能为空！");
        }

        UserInfo userInfo = userInfoDao.getByUserName(userName);
        if(userInfo == null){
            throw new UserException("用户不存在");
        }

        if(!passWord.equals(userInfo.getPassWord())){
            throw new UserException("账号或者密码错误！");
        }
        System.out.println("当前登陆用户:"+userName+"登陆时间:"+new Date() +"-------------------------------->>>");
        request.getSession().setAttribute("userInfo",userInfo);
    }
}
