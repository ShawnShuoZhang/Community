package com.example.community.enums;

/**
 * 通知状态枚举
 *
 * @author Tuoer
 * @date 2023/01/11
 */
public enum NotificationStatusEnum {
    /**
     * 未读
     */
    UNREAD(0),
    /**
     * 读
     */
    READ(1);
    /**
     * 状态
     */
    private final Integer status;

    /**
     * 通知状态枚举
     *
     * @param status 状态
     */
    NotificationStatusEnum(Integer status) {
        this.status = status;
    }

    /**
     * 获得地位
     * 得到状态
     *
     * @return {@link Integer}
     */
    public Integer getStatus() {
        return status;
    }
}
