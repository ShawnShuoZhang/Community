package com.example.community.enums;

/**
 * 自定义错误代码
 *
 * @author Tuoer
 * @date 2023/01/04
 */
public enum CustomizeErrorCode implements com.example.community.exception.CustomizeErrorCode {
    /**
     * 没有发现问题
     */
    QUESTION_NOT_FOUND("你找的问题不在了，要不要换个试试？", 2001),
    /**
     * 目标参数未找到
     */
    TARGET_PARAM_NOT_FOUND("未选中任何问题或评论进行回复", 2002),
    /**
     * 没有登录
     */
    NO_LOGIN("当前操作需要登录，请登录后重试", 2003),
    /**
     * 系统错误
     */
    SYS_ERROR("服务器冒烟了，要不然你稍后再试试！！！", 2004),
    /**
     * 输入参数错误
     */
    TYPE_PARAM_WRONG("评论类型错误或不存在", 2005),
    /**
     * 评论没有找到
     */
    COMMENT_NOT_FOUND("回复的评论不存在了，要不要换个试试？", 2006),
    /**
     * 内容是空
     */
    CONTENT_IS_EMPTY("输入内容不能为空", 2007),
    /**
     * 阅读通知失败
     */
    READ_NOTIFICATION_FAIL("兄弟你这是读别人的信息呢？", 2008),
    /**
     * 通知未找到
     */
    NOTIFICATION_NOT_FOUND("消息莫非是不翼而飞了？", 2009);
    /**
     * 代码
     */
    private final Integer code;
    /**
     * 消息
     */
    private final String message;

    /**
     * 自定义错误代码
     *
     * @param message 消息
     * @param code    代码
     */
    CustomizeErrorCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    /**
     * 得到消息
     *
     * @return {@link String}
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 获取代码
     *
     * @return {@link Integer}
     */
    @Override
    public Integer getCode() {
        return code;
    }

}
