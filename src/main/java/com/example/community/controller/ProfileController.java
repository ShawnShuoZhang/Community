package com.example.community.controller;

import com.example.community.dto.PaginationDto;
import com.example.community.model.User;
import com.example.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 个人中心控制器
 *
 * @author Tuoer
 * @date 2023/01/03
 */
@Controller
public class ProfileController {
    @Autowired
    QuestionService questionService;

    /**
     * 配置文件
     *
     * @param action  行为
     * @param model   模型
     * @param request 请求
     * @param page    页面号
     * @param size    页面大小
     * @return {@link String}
     */
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            model.addAttribute("sign", "question-sign");
            PaginationDto paginationDto = questionService.list(user.getAccountId(), page, size);
            model.addAttribute("pagination", paginationDto);
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            model.addAttribute("sign", "comment");

            PaginationDto paginationDto = questionService.list(user.getAccountId(), page, size);
            model.addAttribute("pagination", paginationDto);
        }
        return "profile";
    }
}
