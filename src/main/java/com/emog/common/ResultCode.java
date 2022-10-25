package com.emog.common;

public enum ResultCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败");

    long code;
    String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}