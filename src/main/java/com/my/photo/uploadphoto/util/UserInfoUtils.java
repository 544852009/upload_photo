package com.my.photo.uploadphoto.util;

import com.my.photo.uploadphoto.Exception.UserException;
import com.my.photo.uploadphoto.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;

public class UserInfoUtils {

    public static UserInfo getCurrentUser(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (userInfo != null) {
            return userInfo;
        } else {
            throw new UserException("您已经掉线");
        }
    }

    public static String getCurrentUserName(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (userInfo != null) {
            return userInfo.getUserName();
        } else {
            throw new UserException("您已经掉线");
        }
    }
}
