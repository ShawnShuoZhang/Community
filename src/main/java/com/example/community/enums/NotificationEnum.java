package com.example.community.enums;

/**
 * 通知枚举
 *
 * @author Tuoer
 * @date 2023/01/11
 */
public enum NotificationEnum {
    /**
     * 回答问题
     */
    REPLY_QUESTION(1, "回复了问题"),
    /**
     * 回复评论
     */
    REPLY_COMMENT(2, "回复了评论");
    /**
     * 类型
     */
    private final Integer type;
    /**
     * 名字
     */
    private final String name;

    /**
     * 通知枚举
     *
     * @param type 类型
     * @param name 名字
     */
    NotificationEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * 类型名字
     *
     * @param type 类型
     * @return {@link String}
     */
    public static String nameOfType(Integer type) {
        for (NotificationEnum notificationEnum : NotificationEnum.values()) {
            if (notificationEnum.getType().equals(type)) {
                return notificationEnum.getName();
            }
        }
        return "";
    }

    /**
     * 得到类型
     *
     * @return {@link Integer}
     */
    public Integer getType() {
        return type;
    }

    /**
     * 得到名字
     *
     * @return {@link String}
     */
    public String getName() {
        return name;
    }
}
