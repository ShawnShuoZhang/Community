package com.example.community;

import com.alibaba.fastjson.JSON;
import com.example.community.mapper.QuestionMapper;
import com.example.community.model.Question;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    QuestionMapper questionMapper;

    @Test
    void contextLoads() {
        PageHelper.startPage(1, 5);
        List<Question> list = questionMapper.list();
        list.forEach(System.out::println);
        PageInfo<Question> questionPageInfo = new PageInfo<>(list);
        System.out.println(questionPageInfo.getPages());
        System.out.println(questionPageInfo.getTotal());
        System.out.println(questionPageInfo.getPageNum());
        System.out.println(questionPageInfo.getPageSize());

    }
}
