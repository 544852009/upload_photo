package com.my.photo.uploadphoto.util;

import java.util.UUID;

public class IDUtil {

    public static String getStringId(){

        return UUID.randomUUID().toString().replace("-","");
    }

    public static long getLongId(){
        return System.currentTimeMillis();
    }
}
