package com.my.photo.uploadphoto.task;

import com.alibaba.fastjson.JSON;
import com.my.photo.uploadphoto.dao.PhotoTypeDao;
import com.my.photo.uploadphoto.entity.PhotoType;
import com.my.photo.uploadphoto.service.UserPhotoTypeService;
import com.my.photo.uploadphoto.websocket.SocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
@Slf4j
public class AutoPutMessageTask {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PhotoTypeDao photoTypeDao;

    @Scheduled(cron = "0 0 0 1/1 * ? ")
    public void putMessage() {

        List<PhotoType> all = photoTypeDao.findAll();
        if (all.size() != 0) {

            Random random = new Random(System.currentTimeMillis());
            int i = random.nextInt(all.size() - 1);

            PhotoType photoType = all.get(i);
            SocketServer.sendAll(JSON.toJSONString(photoType));
        }

        log.info("定时任务执行了" + new Date());
    }


    @Scheduled(cron = "0 0 0/1 1/1 * ? ")
    public void updateWeather() {

        String url = "http://api.map.baidu.com/telematics/v3/weather?location=西安&output=json&ak=EGgzZ22dsboWQEcPQ6KDQLknQd3YkkkP";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        String body = forEntity.getBody();
        Map<String, Object> stringMap = JSON.parseObject(body,Map.class);
        Integer status = (Integer)stringMap.get("status");
        if(302!=status){
            stringRedisTemplate.opsForValue().set(url, forEntity.getBody());
            log.info("写入缓存------------------>" + new Date());
        }else {
            log.info((String) stringMap.get("message")+ new Date());
        }

    }
}
