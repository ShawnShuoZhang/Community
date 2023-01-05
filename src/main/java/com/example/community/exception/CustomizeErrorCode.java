package com.example.community.exception;

/**
 * 自定义错误代码
 *
 * @author Tuoer
 * @date 2023/01/04
 */
public interface CustomizeErrorCode {

    /**
     * 得到消息
     *
     * @return {@link String}
     */
    String getMessage();

    /**
     * 获取代码
     *
     * @return {@link Integer}
     */
    Integer getCode();
}
