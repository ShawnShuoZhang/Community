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
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
}
