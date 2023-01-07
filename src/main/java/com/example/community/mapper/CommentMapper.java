package com.example.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 评论映射器
 *
 * @author Tuoer
 * @date 2023/01/07
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 插入
     *
     * @param comment 评论
     * @return int
     */
    @Insert("insert into comment (parent_id, type, commentator, gmt_create, gmt_modified, like_count, content) values (#{parentId}, #{type}, #{commentator}, #{gmtCreate}, #{gmtModified}, #{likeCount}, #{content})")
    int insert(Comment comment);


    /**
     * 选择父id和类型
     *
     * @param parentId 父id
     * @param type     类型
     * @return {@link List}<{@link Comment}>
     */
    @Select("select * from comment where parent_id = #{parentId} and type = #{type}")
    List<Comment> selectByParentIdAndType(@Param("parentId") String parentId,Integer type);


}