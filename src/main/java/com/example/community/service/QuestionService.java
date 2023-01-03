package com.example.community.service;

import com.example.community.dto.PaginationDto;
import com.example.community.dto.QuestionDto;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题服务
 *
 * @author Tuoer
 * @date 2023/01/01
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取问题列表
     *
     * @param page 页面
     * @param size 大小
     * @return {@link PaginationDto}
     */
    public PaginationDto list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Question> list = questionMapper.list();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        PaginationDto paginationDto = new PaginationDto();
        for (Question question : list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);
        PageInfo<Question> pageInfo = new PageInfo<>(list);
        int pages = pageInfo.getPages();
        long total = pageInfo.getTotal();
        paginationDto.setPagination(pages, total, page, size);
        return paginationDto;
    }
}
