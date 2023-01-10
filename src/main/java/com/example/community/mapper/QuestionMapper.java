package com.example.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.community.dto.QuestionDto;
import com.example.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 问题映射器
 *
 * @author Tuoer
 * @date 2023/01/07
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 创建(插入)
     *
     * @param question 问题
     */
    @Insert("insert into QUESTION (TITLE,DESCRIPTION,TAG,GMT_CREATE,GMT_MODIFIED,CREATOR,COMMENT_COUNT,VIEW_COUNT,LIKE_COUNT) values (#{title},#{description},#{tag},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount})")
    void create(Question question);

    /**
     * 查找问题列表
     *
     * @return {@link List}<{@link Question}>
     */
    @Select("select * from question order by GMT_CREATE desc ")
    List<Question> list();

    /**
     * 根据用户ID查找问题
     *
     * @param userId 用户id
     * @return {@link List}<{@link Question}>
     */
    @Select("select * from question where creator = #{userId}")
    List<Question> listByUserId(@Param(value = "userId") String userId);

    /**
     * 发现通过id
     *
     * @param questionId 问题id
     * @return {@link Question}
     */
    @Select("select * from question where id = #{id}")
    Question findById(@Param(value = "id") Long questionId);

    /**
     * 更新
     * 更新问题
     *
     * @param question 问题
     * @return {@link Integer}
     */
    @Update("update question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
    Integer update(Question question);

    /**
     * 增加阅读数
     *
     * @param questionId 问题id
     */
    @Update("update question set view_count = view_count + 1 where id = #{id}")
    void icView(@Param("id") Long questionId);

    /**
     * 增加评论数
     *
     * @param questionId 问题id
     */
    @Update("update question set comment_count = comment_count + 1 where id = #{id}")
    void icComment(@Param("id") Long questionId);

    /**
     * 相关问题列表
     *
     * @param question 问题
     * @return {@link List}<{@link Question}>
     */
    @Select("select * from question where id != #{id} and tag regexp (select replace(tag, '，', '|') from QUESTION where id = #{id}) order by GMT_CREATE desc limit 20")
    List<Question> relatedQuestionList(QuestionDto question);
}