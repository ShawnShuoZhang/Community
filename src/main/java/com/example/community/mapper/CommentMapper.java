package com.example.community.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.community.model.Comment;
import org.apache.ibatis.annotations.Insert;import org.apache.ibatis.annotations.Mapper;

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
     * 选择由父id
     *
     * @param parentId 父id
     * @return {@link List}<{@link Comment}>
     */


}