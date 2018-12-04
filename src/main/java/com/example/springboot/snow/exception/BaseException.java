package com.example.springboot.snow.exception;

import com.example.springboot.snow.core.Result;

public class BaseException extends Exception {
    private Result result;
    private Throwable cause;
    public BaseException(Result result) {
        super(result.getMessage());
        this.result = result;
    }

    public BaseException(Result returnEnum, Throwable cause) {
        super(returnEnum.getMessage(), cause);
        this.result = returnEnum;
        this.cause = cause;
    }
    public BaseException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    public Result getReturnEnum() {
        return result;
    }
}
