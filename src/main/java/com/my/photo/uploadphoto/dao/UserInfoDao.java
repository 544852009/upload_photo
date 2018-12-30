package com.my.photo.uploadphoto.dao;

import com.my.photo.uploadphoto.entity.UserInfo;
import com.my.photo.uploadphoto.entity.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, Long> {

    public UserInfo getByUserName(String userName);
}
