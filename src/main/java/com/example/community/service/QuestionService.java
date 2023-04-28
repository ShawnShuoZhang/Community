package com.example.community.service;

import com.example.community.dto.PaginationDto;
import com.example.community.dto.QuestionDto;
import com.example.community.enums.CustomizeErrorCode;
import com.example.community.exception.CustomizeException;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 问题服务
 *
 * @author Tuoer
 * @date 2023/01/01
 */
@Service
public class QuestionService {
    /**
     * 问题映射器
     */
    @Autowired
    private QuestionMapper questionMapper;
    /**
     * 用户映射器
     */
    @Autowired
    private UserMapper userMapper;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 列表
     * 获取问题列表
     *
     * @param page   页面
     * @param size   大小
     * @param search
     * @return {@link PaginationDto}<{@link QuestionDto}>
     */
    public PaginationDto<QuestionDto> list(Integer page, Integer size, String search) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey("questions: " + page + ": " + size + ": " + search))) {
            return (PaginationDto<QuestionDto>) redisTemplate.opsForValue().get("questions: " + page + ": " + size + ": " + search);
        } else {
            PageHelper.startPage(page, size);
            List<Question> list = new ArrayList<>();
            if (StringUtils.isBlank(search)) {
                list = questionMapper.list();
            } else {
                String replace = search.replace(" ", "|").toUpperCase();
                list = questionMapper.listBySearch(replace);
            }
            // ListUtil.sortByProperty(list, "gmtCreate");
            redisTemplate.opsForValue().set("questions: " + page + ": " + size + ": " + search, getPaginationDto(page, size, list), 60, TimeUnit.SECONDS);
            return getPaginationDto(page, size, list);
        }
    }

    /**
     * 列表
     * 获取问题列表，根据用户id
     *
     * @param userId 用户id
     * @param page   页面
     * @param size   大小
     * @return {@link PaginationDto}<{@link QuestionDto}>
     */
    public PaginationDto<QuestionDto> list(String userId, Integer page, Integer size) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey("questions: " + userId + ": " + page + ": " + size))) {
            return (PaginationDto<QuestionDto>) redisTemplate.opsForValue().get("questions: " + userId + ": " + page + ": " + size);
        } else {
            PageHelper.startPage(page, size);
            List<Question> list = questionMapper.listByUserId(userId);
            redisTemplate.opsForValue().set("questions: " + userId + ": " + page + ": " + size, getPaginationDto(page, size, list), 60, TimeUnit.SECONDS);
            return getPaginationDto(page, size, list);
        }
    }

    /**
     * 得到分页dto
     *
     * @param page 页面
     * @param size 大小
     * @param list 列表
     * @return {@link PaginationDto}<{@link QuestionDto}>
     */
    @NotNull
    private PaginationDto<QuestionDto> getPaginationDto(Integer page, Integer size, List<Question> list) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey("questions: " + page + ": " + size + ": " + list.hashCode()))) {
            return (PaginationDto<QuestionDto>) redisTemplate.opsForValue().get("questions: " + page + ": " + size + ": " + list.hashCode());
        } else {
            List<QuestionDto> questionDtoList = new ArrayList<>();
            PaginationDto<QuestionDto> paginationDto = new PaginationDto<QuestionDto>();
            for (Question question : list) {
                User user = userMapper.findById(question.getCreator());
                QuestionDto questionDto = new QuestionDto();
                BeanUtils.copyProperties(question, questionDto);
                questionDto.setUser(user);
                questionDtoList.add(questionDto);
            }
            paginationDto.setData(questionDtoList);
            PageInfo<Question> pageInfo = new PageInfo<>(list);
            int pages = pageInfo.getPages();
            long total = pageInfo.getTotal();
            paginationDto.setPagination(pages, total, page, size);
            redisTemplate.opsForValue().set("questions: " + page + ": " + size + ": " + list.hashCode(), paginationDto, 60, TimeUnit.SECONDS);
            return paginationDto;
        }
    }

    /**
     * 发现通过id
     *
     * @param questionId 问题id
     * @return {@link QuestionDto}
     */
    public QuestionDto findById(Long questionId) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey("question: " + questionId))) {
            return (QuestionDto) redisTemplate.opsForValue().get("question: " + questionId);
        } else {
            Question question = questionMapper.findById(questionId);
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            User user = userMapper.findById(question.getCreator());
            questionDto.setUser(user);
            redisTemplate.opsForValue().set("question: " + questionId, questionDto, 60, TimeUnit.SECONDS);
            return questionDto;
        }
    }

    /**
     * 创建
     *
     * @param question 问题
     */
    public void create(Question question) {
        questionMapper.create(question);
    }

    /**
     * 更新
     * 更新问题
     *
     * @param question 问题
     * @return {@link Integer}
     */
    public Integer update(Question question) {
        Integer update = questionMapper.update(question);
        return update;
    }

    /**
     * 集成电路观点
     * 增加阅读数
     *
     * @param questionId 问题id
     */
    public void icView(Long questionId) {
        questionMapper.icView(questionId);
    }

    /**
     * 找到相关问题
     *
     * @param questionDto 问题dto
     * @return {@link List}<{@link Question}>
     */
    public List<Question> findRelatedQuestion(QuestionDto questionDto) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey("relatedQuestion: " + questionDto.getId()))) {
            return (List<Question>) redisTemplate.opsForValue().get("relatedQuestion: " + questionDto.getId());
        } else {
            List<Question> list = questionMapper.relatedQuestionList(questionDto);
            redisTemplate.opsForValue().set("relatedQuestion: " + questionDto.getId(), list, 60, TimeUnit.SECONDS);
            return list;
        }
    }
}

