package com.example.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 网络配置
 *
 * @author Tuoer
 * @date 2023/01/04
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 会话拦截器
     */
    @Autowired
    private SessionInterceptor sessionInterceptor;

    /**
     * 添加拦截器
     *
     * @param registry 注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/*/**")
                .excludePathPatterns("/css/**", "/js/**", "/images/**", "/fonts/**", "/lib/**", "/favicon.ico", "/callback");
    }
}
