package com.example.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通知
 *
 * @author Tuoer
 * @date 2023/01/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "NOTIFICATION")
public class Notification {
    /**
     * 坳dmt创建
     */
    public static final String COL_DMT_CREATE = "DMT_CREATE";
    /**
     * id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
     * 通知人
     */
    @TableField(value = "NOTIFIER")
    private String notifier;

    /**
     * 接收机
     */
    @TableField(value = "RECEIVER")
    private String receiver;

    /**
     * 外部id
     */
    @TableField(value = "OUTER_ID")
    private String outerId;

    /**
     * 类型
     */
    @TableField(value = "\"TYPE\"")
    private Integer type;

    /**
     * 格林尼治时间创建
     */
    @TableField(value = "GMT_CREATE")
    private Long gmtCreate;

    /**
     * 状态
     */
    @TableField(value = "\"STATUS\"")
    private Integer status;

    /**
     * 通知人名称
     */
    @TableField(value = "NOTIFIER_NAME")
    private String notifierName;

    /**
     * 外标题
     */
    @TableField(value = "OUTER_TITLE")
    private String outerTitle;

    /**
     * 坳id
     */
    public static final String COL_ID = "ID";

    /**
     * 坳通知
     */
    public static final String COL_NOTIFIER = "NOTIFIER";

    /**
     * 坳接收机
     */
    public static final String COL_RECEIVER = "RECEIVER";

    /**
     * 山坳外id
     */
    public static final String COL_OUTER_ID = "OUTER_ID";

    /**
     * 坳型
     */
    public static final String COL_TYPE = "TYPE";

    /**
     * 坳格林尼治时间创建
     */
    public static final String COL_GMT_CREATE = "GMT_CREATE";

    /**
     * 坳状态
     */
    public static final String COL_STATUS = "STATUS";

    /**
     * 坳通知人名称
     */
    public static final String COL_NOTIFIER_NAME = "NOTIFIER_NAME";

    /**
     * 山坳外标题
     */
    public static final String COL_OUTER_TITLE = "OUTER_TITLE";
}