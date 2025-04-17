package com.scut.exception;

/**
 * 信息不全异常
 */
public class MissingInformationException extends BaseException {
    public MissingInformationException() {
    }

    public MissingInformationException(String msg) {
        super(msg);
    }
}
