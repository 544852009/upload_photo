package com.my.photo.uploadphoto.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener.class)
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private String passWord;

    private String idCard;

    private String address;

    private String salt;

    /**
     * 创建时间
     */
    @CreatedDate
    private Date createTime;
    /**
     * 创建人
     */
    @CreatedBy
    private Long createBy;
    /**
     * 修改时间
     */
    @LastModifiedDate
    private Date updateTime;
    /**
     * 修改人
     */
    @LastModifiedBy
    private String lastUpdateBy;

}
