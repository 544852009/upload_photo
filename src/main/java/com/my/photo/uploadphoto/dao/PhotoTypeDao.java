package com.my.photo.uploadphoto.dao;

import com.my.photo.uploadphoto.entity.PhotoType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PhotoTypeDao extends JpaRepository<PhotoType, Long> {

    Page<PhotoType> findAll(Specification<PhotoType> specification, Pageable pageable);
}
