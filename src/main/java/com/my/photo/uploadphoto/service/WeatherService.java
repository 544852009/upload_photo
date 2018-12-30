package com.my.photo.uploadphoto.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * 描述:
 * 照片分类
 *
 * @author 54485
 * @create 2018-09-14 13:52
 */
@Service
@Data
@Slf4j
public class WeatherService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    public String getWeather() {
        String url = "http://api.map.baidu.com/telematics/v3/weather?location=西安&output=json&ak=EGgzZ22dsboWQEcPQ6KDQLknQd3YkkkP";
        String content = stringRedisTemplate.opsForValue().get(url);
        log.info("读取缓存------------------>" + new Date());
        return content;
    }
}