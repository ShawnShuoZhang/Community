package com.example.community.dto;

import com.example.community.model.User;
import lombok.Data;

/**
 * 问题dto
 *
 * @author Tuoer
 * @date 2023/01/01
 */
@Data
public class QuestionDto {
    /**
     * id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 格林尼治时间创建
     */
    private Long gmtCreate;
    /**
     * 格林尼治时间修改
     */
    private Long gmtModified;
    /**
     * 创造者
     */
    private String creator;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 视图数
     */
    private Integer viewCount;
    /**
     * 像数
     */
    private Integer likeCount;
    /**
     * 标签
     */
    private String tag;
    /**
     * 用户
     */
    private User user;
}
