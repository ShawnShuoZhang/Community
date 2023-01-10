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
    private Long id;
    private String parentId;
    private Integer type;
    private String commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
    private Integer commentCount;

}
