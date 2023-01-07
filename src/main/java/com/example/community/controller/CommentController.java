package com.example.community.controller;

import com.example.community.dto.CommentCreateDto;
import com.example.community.dto.ResultDto;
import com.example.community.exception.ECustomizeErrorCode;
import com.example.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 评论控制器
 *
 * @author Tuoer
 * @date 2023/01/05
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDto commentCreateDto,
                       HttpSession session) {
        if (session.getAttribute("user") == null) {
            return ResultDto.errorOf(ECustomizeErrorCode.NO_LOGIN);
        }
        commentService.insert(commentCreateDto, session);
        return ResultDto.okOf();
    }
}
