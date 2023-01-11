package com.example.community.dto;


import lombok.Data;

/**
 * 访问令牌dto
 *
 * @author Tuoer
 * @date 2022/12/31
 */
@Data
public class AccessTokenDto {
    /**
     * 客户机id
     */
    private String client_id;
    /**
     * 客户秘密
     */
    private String client_secret;
    /**
     * 代码
     */
    private String code;
    /**
     * 重定向uri
     */
    private String redirect_uri;
}
