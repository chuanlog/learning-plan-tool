package com.scut.exception;

/**
 * 用户名重复异常
 */
public class UserExistException extends BaseException {
    public UserExistException() {
    }

    public UserExistException(String msg) {
        super(msg);
    }
}
