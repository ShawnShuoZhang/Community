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
    private String categoryName;
    private List<String> tags;
}
