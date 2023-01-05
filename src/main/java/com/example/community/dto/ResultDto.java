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
public class ResultDto {
    private Integer code;
    private String message;

    public static ResultDto errorOf(Integer code, String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDto okOf() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    // public static ResultDto okOf(Object data) {
    //     ResultDto resultDto = new ResultDto();
    //     resultDto.setCode(200);
    //     resultDto.setMessage("请求成功");
    //     resultDto.setData(data);
    //     return resultDto;
    // }
}
