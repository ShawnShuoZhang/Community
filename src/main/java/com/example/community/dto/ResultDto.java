package com.example.community.dto;

import com.example.community.exception.CustomizeErrorCode;
import com.example.community.exception.CustomizeException;
import lombok.Data;

/**
 * 结果dto
 *
 * @author Tuoer
 * @date 2023/01/05
 */
@Data
public class ResultDto<T> {
    /**
     * 代码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    /**
     * 错误
     *
     * @param code    代码
     * @param message 消息
     * @return {@link ResultDto}
     */
    public static ResultDto errorOf(Integer code, String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    /**
     * 错误
     *
     * @param errorCode 错误代码
     * @return {@link ResultDto}
     */
    public static ResultDto errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 好了
     *
     * @return {@link ResultDto}
     */
    public static ResultDto okOf() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }

    /**
     * 错误
     *
     * @param e e
     * @return {@link ResultDto}
     */
    public static ResultDto errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    /**
     * 好了
     *
     * @param data 数据
     * @return {@link ResultDto}
     */
    public static <T> ResultDto okOf(T data) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        resultDto.setData(data);
        return resultDto;
    }
}
