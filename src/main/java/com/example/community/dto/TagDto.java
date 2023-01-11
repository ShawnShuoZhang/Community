package com.example.community.dto;

import lombok.Data;

import java.util.List;

/**
 * 标签dto
 *
 * @author Tuoer
 * @date 2023/01/10
 */
@Data
public class TagDto {
    /**
     * 类别名称
     */
    private String categoryName;
    /**
     * 标签
     */
    private List<String> tags;
}
