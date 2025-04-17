package com.scut.exception;

/**
 * 用户不存在异常
 */
public class UserNotExistException extends BaseException {
    public UserNotExistException() {
        super();
    }

    public UserNotExistException(String msg) {
        super(msg);
    }
}
