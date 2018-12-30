package com.my.photo.uploadphoto.weixin;

import com.my.photo.uploadphoto.vo.WXMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 类名称: LoginController
 * 类描述: 与微信对接登陆验证
 *
 * @author yuanjun
 * 创建时间:2017年12月5日上午10:52:13
 */
@Controller
public class WeiXinController {

    @Autowired
    private WXPushMessage wxPushMessage;


    @RequestMapping(value = "wx", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("success");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
                out.write(echostr);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.close();
        }

    }


    @RequestMapping(value = "wx", method = RequestMethod.POST)
    public void huifu(HttpServletRequest request, HttpServletResponse response) {


     

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Map<String, String> map = WXUtils.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String createTime = map.get("CreateTime");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String msgId = map.get("MsgId");
            String event = map.get("Event");

            String message = null;
            if (WXUtils.MESSAGE_TEXT.equals(msgType)) {

                    WXMessage wxMessage = new WXMessage();
                    wxMessage.setToUserName(fromUserName);
                    wxMessage.setFromUserName(toUserName);
                    wxMessage.setCreateTime("2013-12-3");
                    wxMessage.setMsgType("text");
                    wxMessage.setContent("你发送的是:" + content);
                    message = WXUtils.mapToXml(wxMessage);
            }else if("subscribe".equals(event)){
                WXMessage wxMessage = new WXMessage();
                wxMessage.setToUserName(fromUserName);
                wxMessage.setFromUserName(toUserName);
                wxMessage.setCreateTime("2013-12-3");
                wxMessage.setMsgType("text");
                wxMessage.setContent("感谢你的关注!!!");
                message = WXUtils.mapToXml(wxMessage);

                wxPushMessage.pushMessage();
            }

            writer.print(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

    }


}