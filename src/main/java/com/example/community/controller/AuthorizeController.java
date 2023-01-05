package com.example.community.controller;

import com.example.community.dto.AccessTokenDto;
import com.example.community.dto.GithubUser;
import com.example.community.provider.GithubProvider;
import com.example.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权控制器
 *
 * @author Tuoer
 * @date 2023/01/01
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Autowired
    UserService userService;

    /**
     * 回调
     *
     * @param code     代码
     * @param state    状态
     * @param request  请求
     * @param response 响应
     * @return {@link String}
     * @throws IOException ioexception
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        if (githubUser != null && githubUser.getId() != null) {
            // 登录成功
            request.getSession().setAttribute("user", githubUser);
            String token = userService.createOrUpdate(githubUser);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
        } else {
            // 登录失败
        }
        response.sendRedirect("/");
        return null;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
