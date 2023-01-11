package com.example.community.controller;

import com.example.community.dto.NotificationDto;
import com.example.community.enums.NotificationEnum;
import com.example.community.model.User;
import com.example.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通知控制器
 *
 * @author Tuoer
 * @date 2023/01/11
 */
@Controller
public class NotificationController {
    /**
     * 通知服务
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * 配置文件
     *
     * @param id       id
     * @param request  请求
     * @param response 响应
     * @return {@link String}
     */
    @GetMapping("/notification/{id}")
    public String profile(@PathVariable("id") Long id,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDto notificationDto = notificationService.read(id, user);
        if (NotificationEnum.REPLY_COMMENT.getType().equals(notificationDto.getType()) || NotificationEnum.REPLY_QUESTION.getType().equals(notificationDto.getType())) {
            return "redirect:/question/" + notificationDto.getOuterId();
        } else {
            return "redirect:/";
        }
    }
}
