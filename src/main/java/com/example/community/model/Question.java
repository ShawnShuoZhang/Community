package com.example.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 问题
 *
 * @author Tuoer
 * @date 2023/01/07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "QUESTION")
public class Question {
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    @TableField(value = "TITLE")
    private String title;

    @TableField(value = "DESCRIPTION")
    private String description;

    @TableField(value = "GMT_CREATE")
    private Long gmtCreate;

    @TableField(value = "GMT_MODIFIED")
    private Long gmtModified;

    @TableField(value = "CREATOR")
    private String creator;

    @TableField(value = "COMMENT_COUNT")
    private Integer commentCount;

    @TableField(value = "VIEW_COUNT")
    private Integer viewCount;

    @TableField(value = "LIKE_COUNT")
    private Integer likeCount;

    @TableField(value = "TAG")
    private String tag;

    public static final String COL_ID = "ID";

    public static final String COL_TITLE = "TITLE";

    public static final String COL_DESCRIPTION = "DESCRIPTION";

    public static final String COL_GMT_CREATE = "GMT_CREATE";

    public static final String COL_GMT_MODIFIED = "GMT_MODIFIED";

    public static final String COL_CREATOR = "CREATOR";

    public static final String COL_COMMENT_COUNT = "COMMENT_COUNT";

    public static final String COL_VIEW_COUNT = "VIEW_COUNT";

    public static final String COL_LIKE_COUNT = "LIKE_COUNT";

    public static final String COL_TAG = "TAG";
}