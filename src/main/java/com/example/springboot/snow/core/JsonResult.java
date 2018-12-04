package com.example.springboot.snow.core;

import java.io.Serializable;

public class JsonResult <T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public JsonResult(int code,String message,T data){
        this.code=code;
        this.message = message;
        this.data = data;
    }
        public JsonResult(Result returnEnum, T data){
        this.code = returnEnum.getCode();
        this.message = returnEnum.getMessage();
        this.data = data;
    }
    public JsonResult(Result returnEnum){
        this.code = returnEnum.getCode();
        this.message = returnEnum.getMessage();
    }



    public static JsonResult success(){
        return new JsonResult(Result.success.getCode(),Result.success.getMessage(),null);
    }
    public static <T> JsonResult success(T data){
        return new JsonResult(Result.success.getCode(),Result.success.getMessage(),data);
    }
    public static <T> JsonResult successMsg(String data){
        return new JsonResult(Result.success.getCode(),data,"");
    }

    public static JsonResult error(){
        return new JsonResult(Result.error.getCode(),Result.error.getMessage(),null);
    }
    public static <T> JsonResult error(Result returnEnum){
        return new JsonResult(returnEnum.getCode(),returnEnum.getMessage(),null);
    }
    public static <T> JsonResult error(Result returnEnum,T data){
        return new JsonResult(returnEnum.getCode(),returnEnum.getMessage(),data);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
