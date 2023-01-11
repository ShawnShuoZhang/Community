package com.example.community.dto;

import lombok.Data;

/**
 * github用户
 *
 * @author Tuoer
 * @date 2022/12/31
 */
@Data
public class GithubUser {
    /**
     * 名字
     */
    private String name;
    /**
     * id
     */
    private Long id;
    /**
     * 生物
     */
    private String bio;
    /**
     * 《阿凡达》url
     */
    private String avatarUrl;

    /**
     * 字符串
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
