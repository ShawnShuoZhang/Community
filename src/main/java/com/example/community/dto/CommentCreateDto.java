package com.example.community.dto;

import lombok.Data;

/**
 * 评论创建dto
 *
 * @author Tuoer
 * @date 2023/01/05
 */
@Data
public class CommentCreateDto {
    /**
     * 内容
     */
    private String content;
    /**
     * 父id
     */
    private String parentId;
    /**
     * 类型
     */
    private Integer type;
}
