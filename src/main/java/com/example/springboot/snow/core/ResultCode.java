package com.example.springboot.snow.core;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200,"成功"),//成功
    FAIL(400,"失败"),//失败
    UNAUTHORIZED(401,"未认证（签名错误）"),//未认证（签名错误）
    NOT_FOUND(404,"接口不存在"),//接口不存在
    INTERNAL_SERVER_ERROR(500,"服务器内部错误"),//服务器内部错误
    ACCOUNT_PW_NOT_FOUND (-104,"帐号或密码错误");//帐号或密码错误
    private final int code;
    private final String message;
    ResultCode(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }
    public String message() {
        return message;
    }
}
