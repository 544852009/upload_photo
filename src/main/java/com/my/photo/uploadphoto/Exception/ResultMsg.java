package com.my.photo.uploadphoto.Exception;

import lombok.Data;

@Data
public class ResultMsg {

    private int code;
    private String msg;

    public ResultMsg() {
    }

    public ResultMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
