package com.my.photo.uploadphoto.controller;

import com.my.photo.uploadphoto.Exception.UserResult;
import com.my.photo.uploadphoto.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 天气预报
 *
 * @author 54485
 * @create 2018-09-26 15:00
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService ;

    @PostMapping("/getWeather")
    public UserResult getWeather(){
        String weather = weatherService.getWeather();
        return UserResult.success("获取成功",weather);
    }
}
