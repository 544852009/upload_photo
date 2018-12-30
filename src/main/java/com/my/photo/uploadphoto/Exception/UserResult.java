package com.my.photo.uploadphoto.Exception;


import lombok.Data;

@Data
public  class UserResult {

    private int code;
    private int status;
    private String msg;
    private Object data;


    public UserResult(String msg) {
        this.msg = msg;
    }

    public UserResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public UserResult(int code, int status, String msg, Object data) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }



    public static UserResult success(String msg, Object data){
        return new UserResult(200,1,msg,data);
    }

    public static UserResult success(String msg){
        return new UserResult(1,msg);
    }

    public static UserResult error(ResultMsg resultMsg,String msg, Object data){
        return new UserResult(resultMsg.getCode(),0,resultMsg.getMsg(),data);
    }

    public static UserResult error(String msg){
        return new UserResult(0,msg);
    }
}
