package com.example.community.advice;

import com.example.community.dto.ResultDto;
import com.example.community.exception.CustomizeException;
import com.example.community.enums.CustomizeErrorCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义异常处理程序
 *
 * @author Tuoer
 * @date 2023/01/04
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Object handle(Throwable ex, Model model, HttpServletRequest request) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            // 返回JSON
            if (ex instanceof CustomizeException) {
                return ResultDto.errorOf((CustomizeException) ex);
            } else {
                return ResultDto.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
        } else {
            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
