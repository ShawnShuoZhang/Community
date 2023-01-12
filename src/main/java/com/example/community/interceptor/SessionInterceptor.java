package com.example.community.interceptor;

import com.example.community.enums.CustomizeErrorCode;
import com.example.community.exception.CustomizeException;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.service.NotificationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 会话拦截器
 *
 * @author Tuoer
 * @date 2023/01/04
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {
    /**
     * 用户映射器
     */
    @Autowired
    UserMapper userMapper;
    @Autowired
    NotificationService notificationService;

    /**
     * 前处理
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理程序
     * @return boolean
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        System.out.println("cookie is" + Arrays.toString(cookies));
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        System.out.println("user is" + user.getAccountId());
                        Long unreadCount = notificationService.unreadCount(user.getAccountId());
                        request.getSession().setAttribute("unreadCount", unreadCount);
                    }
                    break;
                }
            }
        }
        if (request.getSession().getAttribute("user") == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        return true;
    }

    /**
     * 处理后
     *
     * @param request      请求
     * @param response     响应
     * @param handler      处理程序
     * @param modelAndView 模型和视图
     * @throws Exception 异常
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 完成后
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理程序
     * @param ex       前女友
     * @throws Exception 异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
