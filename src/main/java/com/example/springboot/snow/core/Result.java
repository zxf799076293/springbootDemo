package com.example.springboot.snow.core;

/**
 * 统一API响应结果封装
 */
public enum  Result {
    success(200,"",""),
    error(500,"error","后台发生未知错误"),
    NOT_FOUND(404,"","接口不存在");//接口不存在
    //自定义异常码
    private int code;
    //国际化文件中的key
    private String type;
    //异常信息说明
    private String message;

    Result(int code, String type, String message){
        this.code = code;
        this.type = type;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ReturnEnum{" +
                "code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
