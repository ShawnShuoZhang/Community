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
    private String content;
    private String parentId;
    private Integer type;
}
