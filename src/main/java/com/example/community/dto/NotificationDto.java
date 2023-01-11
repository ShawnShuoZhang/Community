package com.example.community.dto;

import lombok.Data;

/**
 * 通知dto
 *
 * @author Tuoer
 * @date 2023/01/11
 */
@Data
public class NotificationDto {
    /**
     * id
     */
    private Long id;
    /**
     * 格林尼治时间创建
     */
    private Long gmtCreate;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 通知人
     */
    private String notifier;
    /**
     * 通知人名称
     */
    private String notifierName;
    /**
     * 外标题
     */
    private String outerTitle;
    /**
     * 外部id
     */
    private String outerId;
    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 类型
     */
    private Integer type;
}
