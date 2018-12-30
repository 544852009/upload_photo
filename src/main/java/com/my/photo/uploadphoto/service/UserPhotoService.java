package com.my.photo.uploadphoto.service;

import com.my.photo.uploadphoto.Exception.UserException;
import com.my.photo.uploadphoto.dao.UserPhotoDao;
import com.my.photo.uploadphoto.entity.UserPhoto;
import com.my.photo.uploadphoto.util.IDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserPhotoService {

    @Autowired
    private UserPhotoDao userPhotoDao;

//    @Autowired
//    private QiNiuUtil qiNiuUtil ;

    private Logger logger = LoggerFactory.getLogger(UserPhotoTypeService.class);


    public void savePhoto(List<ObjectError> allErrors, UserPhoto userPhoto) {

        if (allErrors.size() > 0) {
            for (ObjectError objectError : allErrors) {
                FieldError fieldError = (FieldError) objectError;
                throw new UserException(fieldError.getDefaultMessage());
            }
        } else {
            userPhoto.setId(IDUtil.getLongId());
            userPhotoDao.save(userPhoto);
        }

    }

    public void savePhotos(UserPhoto userPhoto) {
        userPhoto.setId(IDUtil.getLongId());
        userPhotoDao.save(userPhoto);

    }

    public List<UserPhoto> findPhotoList() {
        return userPhotoDao.findAll();
    }

    public List<UserPhoto> findPhotoListByUserName(String userName) {
        return userPhotoDao.findByUserNameOrderByCreateTimeDesc(userName);
    }

    public List<UserPhoto> findPhotoLunBoList(String imgType) {
        return userPhotoDao.findByImgTypeOrderByCreateTimeDesc(imgType);
    }


    public Page<UserPhoto> userPhotoPageList(int page, int pageSize, String parentId) {

        //单纯根据页码和页码大小
        Pageable pageable = new PageRequest(page, pageSize);

        Specification<UserPhoto> specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Predicate predicate = criteriaBuilder.equal(root.get("parentId"), parentId);

                return criteriaBuilder.and(predicate);
            }
        };
//        //直接根据Direction 和字段名进行排序
//        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "createDate");

//        //根据sort对象进行排序
//        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
//        Pageable pageable = new PageRequest(page, pageSize, sort);
        Page<UserPhoto> photoPage = userPhotoDao.findAll(specification, pageable);


        return photoPage;
    }


    public void delete(UserPhoto userPhoto) {
        String url = userPhoto.getImgUrl();
        String key = url.substring(url.lastIndexOf("/") + 1);

//        BucketManager deleteManger = qiNiuUtil.getDeleteManger();
//        try {
//            deleteManger.delete(qiNiuUtil.getBucket(),key);
//        } catch (QiniuException e) {
//            e.printStackTrace();
//            throw new UserException("删除失败");
//        }

        userPhotoDao.delete(userPhoto);
    }
}
