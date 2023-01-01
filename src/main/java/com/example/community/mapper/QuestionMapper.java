package com.example.community.mapper;

import com.example.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 问题映射器
 *
 * @author Tuoer
 * @date 2023/01/01
 */
@Mapper
public interface QuestionMapper {
    /**
     * 创建(插入)
     *
     * @param question 问题
     */
    @Insert("insert into question (title, description, gmt_create, gmt_modified, creator, tag) values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

    /**
     * 查找问题列表
     *
     * @return {@link List}<{@link Question}>
     */
    @Select("select * from question")
    List<Question> list();
}
