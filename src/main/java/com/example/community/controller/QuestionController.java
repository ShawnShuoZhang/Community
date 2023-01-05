package com.example.community.controller;

import com.example.community.dto.QuestionDto;
import com.example.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 问题控制器
 *
 * @author Tuoer
 * @date 2023/01/04
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long questionId,
                           Model model) {
        QuestionDto questionDto = questionService.findById(questionId);
        model.addAttribute("question", questionDto);
        questionService.icView(questionId);
        return "question";
    }
}
