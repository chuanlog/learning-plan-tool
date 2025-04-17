package com.scut.exception;

/**
 * 密码错误异常
 */
public class PasswordErrorException extends RuntimeException {
    public PasswordErrorException() {
        super();
    }

    public PasswordErrorException(String msg) {
        super(msg);
    }
}
