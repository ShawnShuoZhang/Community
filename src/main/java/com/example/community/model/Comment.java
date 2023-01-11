package com.example.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论
 *
 * @author Tuoer
 * @date 2023/01/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "\"COMMENT\"")
public class Comment {
    /**
     * id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
     * 父id
     */
    @TableField(value = "PARENT_ID")
    private String parentId;

    /**
     * 类型
     */
    @TableField(value = "\"TYPE\"")
    private Integer type;

    /**
     * 评论员
     */
    @TableField(value = "COMMENTATOR")
    private String commentator;

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
     * 像数
     */
    @TableField(value = "LIKE_COUNT")
    private Long likeCount;

    /**
     * 内容
     */
    @TableField(value = "CONTENT")
    private String content;

    /**
     * 评论数
     */
    @TableField(value = "COMMENT_COUNT")
    private Integer commentCount;

    /**
     * 坳id
     */
    public static final String COL_ID = "ID";

    /**
     * 坳父id
     */
    public static final String COL_PARENT_ID = "PARENT_ID";

    /**
     * 坳型
     */
    public static final String COL_TYPE = "TYPE";

    /**
     * 坳评论员
     */
    public static final String COL_COMMENTATOR = "COMMENTATOR";

    /**
     * 坳格林尼治时间创建
     */
    public static final String COL_GMT_CREATE = "GMT_CREATE";

    /**
     * 坳格林尼治时间修改
     */
    public static final String COL_GMT_MODIFIED = "GMT_MODIFIED";

    /**
     * 上校像数
     */
    public static final String COL_LIKE_COUNT = "LIKE_COUNT";

    /**
     * 坳内容
     */
    public static final String COL_CONTENT = "CONTENT";

    /**
     * 坳评论数
     */
    public static final String COL_COMMENT_COUNT = "COMMENT_COUNT";
}