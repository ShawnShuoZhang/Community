package com.example.community.interceptor;

import com.example.community.exception.CustomizeException;
import com.example.community.exception.ECustomizeErrorCode;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
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
    @Autowired
    UserMapper userMapper;

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
                    }
                    break;
                }
            }
        }
        if (request.getSession().getAttribute("user") == null) {
            throw new CustomizeException(ECustomizeErrorCode.NO_LOGIN);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
