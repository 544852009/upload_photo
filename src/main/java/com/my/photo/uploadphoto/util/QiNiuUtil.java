//package com.my.photo.uploadphoto.util;
//
//import com.qiniu.common.Zone;
//import com.qiniu.storage.BucketManager;
//import com.qiniu.storage.Configuration;
//import com.qiniu.util.Auth;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * 描述:
// * 七牛帮助类
// *
// * @author 54485
// * @create 2018-09-20 11:08
// */
//@Component
//public class QiNiuUtil {
//
//    @Value("${qiniu.accessKey}")
//    private String accessKey;
//    @Value("${qiniu.secretKey}")
//    private String secretKey;
//    @Value("${qiniu.bucket}")
//    private String bucket;
//
//
//    public String getAccessKey() {
//        return accessKey;
//    }
//
//    public void setAccessKey(String accessKey) {
//        this.accessKey = accessKey;
//    }
//
//    public String getSecretKey() {
//        return secretKey;
//    }
//
//    public void setSecretKey(String secretKey) {
//        this.secretKey = secretKey;
//    }
//
//    public String getBucket() {
//        return bucket;
//    }
//
//    public void setBucket(String bucket) {
//        this.bucket = bucket;
//    }
//
//    public BucketManager getDeleteManger(){
//
//        Auth auth = Auth.create(accessKey, secretKey);
//        Configuration config = new Configuration(Zone.autoZone());
//        return new BucketManager(auth, config);
//    }
//}
