package com.example.springboot.snow.exception;


import com.example.springboot.snow.core.JsonResult;
import com.example.springboot.snow.core.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public JsonResult<Result> exceptionHandler(HttpServletRequest request, Exception e) {
        Throwable throwable = getBusinessException(e);
        if (!Objects.isNull(throwable)) {
            Result returnEnum = Result.error;
            return JsonResult.error(returnEnum,throwable);
        }
        return JsonResult.error();
    }

    @ExceptionHandler(value = BaseException.class)
    public JsonResult<Result> businessException(HttpServletRequest request,Exception e){
        BaseException businessException = (BaseException)e;
        return JsonResult.error(Result.error, businessException);
    }
    /**
     * 若有异常进行嵌套，打印出每个异常的堆栈信息，若包含自定义异常，返回最内部的BusinessException异常。
     * @param e
     * @return
     */
    private Throwable getBusinessException(Throwable e) {
        if (e == null) {
            return null;
        } else if (e instanceof BaseException) {
            if(((BaseException)e).getReturnEnum()!=null) {
                logger.info(((BaseException) e).getReturnEnum().toString());
            }
            e.printStackTrace();
            Throwable temp = getBusinessException(e.getCause());
            if (temp == null) {
                return e;
            } else {
                return temp;
            }
        } else {
            e.printStackTrace();
            return getBusinessException(e.getCause());
        }
    }
}
