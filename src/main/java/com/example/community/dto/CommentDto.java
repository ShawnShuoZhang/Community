package com.example.community.dto;

import lombok.Data;

/**
 * 评论dto
 *
 * @author Tuoer
 * @date 2023/01/05
 */
@Data
public class CommentDto {
    private String content;
    private String parentId;
    private Integer type;

}
