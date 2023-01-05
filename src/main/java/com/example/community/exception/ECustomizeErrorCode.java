package com.example.community.exception;

/**
 * 自定义错误代码
 *
 * @author Tuoer
 * @date 2023/01/04
 */
public enum ECustomizeErrorCode implements CustomizeErrorCode {
    /**
     * 没有发现问题
     */
    QUESTION_NOT_FOUND("你找的问题不在了，要不要换个试试？", 2001),
    TARGET_PARAM_NOT_FOUND("未选中任何问题或评论进行回复", 2002),
    NO_LOGIN("当前操作需要登录，请登录后重试", 2003),
    SYS_ERROR("服务器冒烟了，要不然你稍后再试试！！！", 2004),
    TYPE_PARAM_WRONG("评论类型错误或不存在", 2005),
    COMMENT_NOT_FOUND("回复的评论不存在了，要不要换个试试？", 2006);
    private final Integer code;
    private final String message;

    ECustomizeErrorCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
