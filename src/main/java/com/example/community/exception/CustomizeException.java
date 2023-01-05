package com.example.community.exception;

import lombok.Data;

/**
 * 自定义异常
 *
 * @author Tuoer
 * @date 2023/01/04
 */
@Data
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
