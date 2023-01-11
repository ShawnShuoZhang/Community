package com.example.community.controller;

import com.example.community.dto.PaginationDto;
import com.example.community.dto.QuestionDto;
import com.example.community.service.NotificationService;
import com.example.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 指数控制器
 *
 * @author Tuoer
 * @date 2023/01/12
 */
@Controller
public class IndexController {
    /**
     * 问题服务
     */
    @Autowired
    QuestionService questionService;
    /**
     * 通知服务
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * 指数
     *
     * @param model 模型
     * @param page  页面
     * @param size  大小
     * @return {@link String}
     */
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationDto<QuestionDto> pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
