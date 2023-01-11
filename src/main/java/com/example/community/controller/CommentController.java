package com.example.community.controller;

import com.example.community.dto.CommentCreateDto;
import com.example.community.dto.CommentDto;
import com.example.community.dto.ResultDto;
import com.example.community.enums.CommentTypeEnum;
import com.example.community.enums.CustomizeErrorCode;
import com.example.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 评论控制器
 *
 * @author Tuoer
 * @date 2023/01/05
 */
@Controller
public class CommentController {
    /**
     * 评论服务
     */
    @Autowired
    private CommentService commentService;

    /**
     * 帖子
     * post提交
     *
     * @param commentCreateDto 评论创建dto
     * @param session          会话
     * @return {@link Object}
     */
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDto commentCreateDto,
                       HttpSession session) {
        if (session.getAttribute("user") == null) {
            return ResultDto.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if (commentCreateDto == null || StringUtils.isBlank(commentCreateDto.getContent())) {
            return ResultDto.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        commentService.insert(commentCreateDto, session, CommentTypeEnum.COMMENT.getType());
        return ResultDto.okOf();
    }

    /**
     * 评论
     *
     * @param id id
     * @return {@link ResultDto}<{@link List}<{@link CommentDto}>>
     */
    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDto<List<CommentDto>> comments(@PathVariable Long id) {
        List<CommentDto> byParentId = commentService.findByParentId(id, CommentTypeEnum.COMMENT);

        return ResultDto.okOf(byParentId);
    }
}
