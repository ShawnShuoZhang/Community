package com.example.community.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户
 *
 * @author 张硕
 * @date 2022/12/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    /**
     * id
     */
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 帐户id
     */
    private String accountId;
    /**
     * 令牌
     */
    private String token;
    /**
     * 格林尼治时间创建
     */
    private Long gmtCreate;
    /**
     * 格林尼治时间修改
     */
    private Long gmtModified;
    /**
     * 《阿凡达》url
     */
    private String avatarUrl;
}
