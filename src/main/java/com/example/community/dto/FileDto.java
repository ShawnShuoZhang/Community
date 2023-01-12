package com.example.community.dto;

import lombok.Data;

/**
 * 文件dto
 *
 * @author Tuoer
 * @date 2023/01/12
 */
@Data
public class FileDto {
    /**
     * 消息
     */
    private String message;
    /**
     * 成功
     */
    private int success;
    /**
     * url
     */
    private String url;
}
