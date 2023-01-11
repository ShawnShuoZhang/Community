package com.example.community.service;

import com.example.community.dto.GithubUser;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * 用户服务
 *
 * @author Tuoer
 * @date 2023/01/04
 */
@Service
public class UserService {
    /**
     * 用户映射器
     */
    @Autowired
    UserMapper userMapper;

    /**
     * 通知服务
     */
    @Autowired
    NotificationService notificationService;

    /**
     * 创建或更新
     *
     * @param githubUser github用户
     * @param session    会话
     * @return {@link String}
     */
    public String createOrUpdate(GithubUser githubUser, HttpSession session) {
        User byAccountId = userMapper.findById(String.valueOf(githubUser.getId()));
        session.setAttribute("unreadCount", notificationService.unreadCount(String.valueOf(githubUser.getId())));
        String token = UUID.randomUUID().toString();
        if (byAccountId == null) {
            // 插入数据库
            User user = new User();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userMapper.insert(user);
        } else {
            // 更新数据库
            byAccountId.setGmtModified(System.currentTimeMillis());
            byAccountId.setToken(token);
            userMapper.update(byAccountId);
        }
        return token;
    }
}
