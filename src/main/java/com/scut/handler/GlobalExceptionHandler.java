package com.scut.handler;


import com.scut.constant.MessageConstant;
import com.scut.exception.BaseException;
import com.scut.result.Result;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * SQL完整性约束异常处理（唯一约束字段重复等问题）
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error("异常信息：{}", ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");//截取username
            String msg = split[2] + MessageConstant.USER_ALREADY_EXISTS;//拼接异常信息
            return Result.error(msg);
        }
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }

    /**
     * 捕获JWT过期异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(ExpiredJwtException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error("登录令牌过时，请重新登录");
    }
    /**
     * 捕获未知异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(Exception ex){
        if(!Objects.equals(ex.getMessage(), "No endpoint GET /favicon.ico.")){//排除swagger奇奇怪怪的异常
            ex.printStackTrace();
        }
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}
