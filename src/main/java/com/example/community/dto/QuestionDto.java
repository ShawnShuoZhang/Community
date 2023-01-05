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
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private String creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
