package com.example.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "\"COMMENT\"")
public class Comment {
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    @TableField(value = "PARENT_ID")
    private String parentId;

    /**
     * 父类类型
     */
    @TableField(value = "\"TYPE\"")
    private Integer type;

    /**
     * 评论人id
     */
    @TableField(value = "COMMENTATOR")
    private String commentator;

    /**
     * 创建时间
     */
    @TableField(value = "GMT_CREATE")
    private Long gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "GMT_MODIFIED")
    private Long gmtModified;

    /**
     * 点赞数
     */
    @TableField(value = "LIKE_COUNT")
    private Long likeCount;

    @TableField(value = "CONTENT")
    private String content;

    public static final String COL_ID = "ID";

    public static final String COL_PARENT_ID = "PARENT_ID";

    public static final String COL_TYPE = "TYPE";

    public static final String COL_COMMENTATOR = "COMMENTATOR";

    public static final String COL_GMT_CREATE = "GMT_CREATE";

    public static final String COL_GMT_MODIFIED = "GMT_MODIFIED";

    public static final String COL_LIKE_COUNT = "LIKE_COUNT";

    public static final String COL_CONTENT = "CONTENT";
}