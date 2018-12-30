package com.my.photo.uploadphoto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2   //增加这一行注解就可以了
@EnableJpaAuditing
@EnableScheduling
public class UploadPhotoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadPhotoApplication.class, args);
    }
}
