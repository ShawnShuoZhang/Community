package com.example.community.enums;

/**
 * 注释类型枚举
 *
 * @author Tuoer
 * @date 2023/01/05
 */
public enum CommentTypeEnum {
    /**
     * 问题
     */
    QUESTION(1),
    /**
     * 评论
     */
    COMMENT(2);
    /**
     * 类型
     */
    private Integer type;

    /**
     * 注释类型枚举
     *
     * @param type 类型
     */
    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    /**
     * 是存在
     *
     * @param type 类型
     * @return boolean
     */
    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到类型
     *
     * @return {@link Integer}
     */
    public Integer getType() {
        return type;
    }
}
