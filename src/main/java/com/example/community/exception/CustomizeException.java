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
    /**
     * 消息
     */
    private String message;
    /**
     * 代码
     */
    private Integer code;

    /**
     * 自定义异常
     *
     * @param errorCode 错误代码
     */
    public CustomizeException(CustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    /**
     * 得到消息
     *
     * @return {@link String}
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 获取代码
     *
     * @return {@link Integer}
     */
    public Integer getCode() {
        return code;
    }
}
