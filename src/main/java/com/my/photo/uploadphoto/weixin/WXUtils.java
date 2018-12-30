package com.my.photo.uploadphoto.weixin;

import com.my.photo.uploadphoto.vo.WXMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * xml解析
 *
 * @author 54485
 * @create 2018-09-25 16:05
 */
public class WXUtils {

    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VEDIO = "vedio";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "click";
    public static final String MESSAGE_VIEW = "view";

    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {

        Map<String, String> map = new HashMap<>();
        SAXReader saxReader = new SAXReader();
        ServletInputStream inputStream = request.getInputStream();
        Document document = saxReader.read(inputStream);

        Element element = document.getRootElement();
        List<Element> elements = element.elements();

        for (Element ele : elements) {
            map.put(ele.getName(), ele.getText());
        }

        return map;

    }


    public static String mapToXml(WXMessage message) throws IOException, DocumentException {
        XStream xStream = new XStream();
        xStream.alias("xml",message.getClass());
        return xStream.toXML(message);
    }


}
