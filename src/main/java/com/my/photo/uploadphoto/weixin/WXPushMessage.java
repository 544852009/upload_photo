package com.my.photo.uploadphoto.weixin;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WXPushMessage {

    @Autowired
    private  WxMpService wxMpService;

    public  void pushMessage(){

        WxMpTemplateMessage wxMpTemplate = new WxMpTemplateMessage();
        wxMpTemplate.setToUser("oIsHc0SMZ3RgozTroZDSnQLmFKd8");
        wxMpTemplate.setTemplateId("JHQb2BzhEtBIa1r59PZbGVEEvKdf8TILkKWQ9xq15ko");
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplate);
            List<WxMpTemplateData> data = new ArrayList<>();
            data = Arrays.asList(new WxMpTemplateData("first","绑定你的OA账号"),
                    new WxMpTemplateData("state","已完成绑定"));
            wxMpTemplate.setData(data);
            wxMpTemplate.setTemplateId("WKtwQzRfpZwKAXrUUAhNoqvksjWogLYmnu49zVey53E");
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplate);

        } catch (WxErrorException e) {
            System.out.println("推送失败");
        }
    }
}
