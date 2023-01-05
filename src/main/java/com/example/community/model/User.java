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
    private Long id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
