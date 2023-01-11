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
    /**
     * id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "TITLE")
    private String title;

    /**
     * 描述
     */
    @TableField(value = "DESCRIPTION")
    private String description;

    /**
     * 格林尼治时间创建
     */
    @TableField(value = "GMT_CREATE")
    private Long gmtCreate;

    /**
     * 格林尼治时间修改
     */
    @TableField(value = "GMT_MODIFIED")
    private Long gmtModified;

    /**
     * 创造者
     */
    @TableField(value = "CREATOR")
    private String creator;

    /**
     * 评论数
     */
    @TableField(value = "COMMENT_COUNT")
    private Integer commentCount;

    /**
     * 视图数
     */
    @TableField(value = "VIEW_COUNT")
    private Integer viewCount;

    /**
     * 像数
     */
    @TableField(value = "LIKE_COUNT")
    private Integer likeCount;

    /**
     * 标签
     */
    @TableField(value = "TAG")
    private String tag;

    /**
     * 坳id
     */
    public static final String COL_ID = "ID";

    /**
     * 坳标题
     */
    public static final String COL_TITLE = "TITLE";

    /**
     * 上校描述
     */
    public static final String COL_DESCRIPTION = "DESCRIPTION";

    /**
     * 坳格林尼治时间创建
     */
    public static final String COL_GMT_CREATE = "GMT_CREATE";

    /**
     * 坳格林尼治时间修改
     */
    public static final String COL_GMT_MODIFIED = "GMT_MODIFIED";

    /**
     * 上校创造者
     */
    public static final String COL_CREATOR = "CREATOR";

    /**
     * 坳评论数
     */
    public static final String COL_COMMENT_COUNT = "COMMENT_COUNT";

    /**
     * 坳视图数
     */
    public static final String COL_VIEW_COUNT = "VIEW_COUNT";

    /**
     * 上校像数
     */
    public static final String COL_LIKE_COUNT = "LIKE_COUNT";

    /**
     * 坳标签
     */
    public static final String COL_TAG = "TAG";
}