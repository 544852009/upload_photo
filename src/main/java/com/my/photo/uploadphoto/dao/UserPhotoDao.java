package com.my.photo.uploadphoto.dao;

import com.my.photo.uploadphoto.entity.UserPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserPhotoDao extends JpaRepository<UserPhoto, Long> {

    public List<UserPhoto> findByImgTypeOrderByCreateTimeDesc(String imgType);

    public List<UserPhoto> findByUserNameOrderByCreateTimeDesc(String userName);


    Page<UserPhoto> findAll(Specification specification, Pageable pageable);

    int deleteByParentId(String parentId);

}
