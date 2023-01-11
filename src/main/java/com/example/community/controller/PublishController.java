package com.example.community.controller;

import com.example.community.cache.TagCache;
import com.example.community.dto.QuestionDto;
import com.example.community.enums.CustomizeErrorCode;
import com.example.community.exception.CustomizeException;
import com.example.community.model.Question;
import com.example.community.model.User;
import com.example.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 发布控制器
 *
 * @author Tuoer
 * @date 2023/01/01
 */
@Controller
public class PublishController {
    /**
     * 问题服务
     */
    @Autowired
    QuestionService questionService;

    /**
     * 编辑
     * 编辑问题
     *
     * @param id      id
     * @param model   模型
     * @param session 会话
     * @return {@link String}
     */
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model,
                       HttpSession session) {
        QuestionDto question = questionService.findById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        session.setAttribute("id", question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    /**
     * 发布
     * 打开发布页面
     *
     * @param model 模型
     * @return {@link String}
     */
    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    /**
     * 做出版
     * 进行发布动作
     *
     * @param title       标题
     * @param description 描述
     * @param tag         标签
     * @param session     会话
     * @param model       模型
     * @return {@link String}
     */
    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            HttpSession session,
                            Model model) {
        model.addAttribute("tags", TagCache.get());
        Long id = (Long) session.getAttribute("id");
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || "".equals(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || "".equals(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        //todo
        String filterInvalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(filterInvalid)) {
            model.addAttribute("error", "输入非法标签" + filterInvalid);
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        question.setCreator(user.getAccountId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        if (id == null) {
            question.setCommentCount(0);
            question.setViewCount(0);
            question.setLikeCount(0);
            questionService.create(question);
        } else {
            question.setId(id);
            Integer update = questionService.update(question);
            session.setAttribute("id", null);
            if (update != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
        return "redirect:/";
    }
}
