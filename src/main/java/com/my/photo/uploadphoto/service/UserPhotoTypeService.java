package com.my.photo.uploadphoto.service;

import com.my.photo.uploadphoto.dao.PhotoTypeDao;
import com.my.photo.uploadphoto.dao.UserPhotoDao;
import com.my.photo.uploadphoto.entity.PhotoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

/**
 * 描述:
 * 照片分类
 *
 * @author 54485
 * @create 2018-09-14 13:52
 */
@Service
@Transactional
public class UserPhotoTypeService {

    @Autowired
    private PhotoTypeDao photoTypeDao;

    @Autowired
    private UserPhotoDao userPhotoDao ;


    public Page<PhotoType> getPhotoTypes(int page, int pageSize,int imgType) {

        //单纯根据页码和页码大小
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, pageSize, sort);
        Specification<PhotoType> specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Predicate predicate = criteriaBuilder.equal(root.get("imgType"), imgType);

                return criteriaBuilder.and(predicate);
            }
        };
//        //直接根据Direction 和字段名进行排序
//        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "createDate");

//        //根据sort对象进行排序
//        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
//        Pageable pageable = new PageRequest(page, pageSize, sort);
        Page<PhotoType> photoPage = photoTypeDao.findAll(specification,pageable);

        return photoPage;

    }

    public void deleteAll(Long parentId) {

        PhotoType photoType = photoTypeDao.getOne(parentId);
        if(photoType != null){
            photoTypeDao.delete(photoType);
        }

        userPhotoDao.deleteByParentId(String.valueOf(parentId));

    }
}