package com.example.community.dto;

import com.example.community.model.User;
import lombok.Data;

/**
 * 评论dto
 *
 * @author Tuoer
 * @date 2023/01/07
 */
@Data
public class CommentDto {
    /**
     * id
     */
    private Long id;
    /**
     * 父id
     */
    private String parentId;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 评论员
     */
    private String commentator;
    /**
     * 格林尼治时间创建
     */
    private Long gmtCreate;
    /**
     * 格林尼治时间修改
     */
    private Long gmtModified;
    /**
     * 像数
     */
    private Long likeCount;
    /**
     * 内容
     */
    private String content;
    /**
     * 用户
     */
    private User user;
    /**
     * 评论数
     */
    private Integer commentCount;

}
