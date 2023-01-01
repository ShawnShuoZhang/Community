package com.example.community.mapper;

import com.example.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户映射器
 *
 * @author Tuoer
 * @date 2023/01/01
 */
@Mapper
public interface UserMapper {
    /**
     * 插入
     *
     * @param user 用户
     */
    @Insert("insert into user_community (name, account_id, token, gmt_create, gmt_modified,avatar_url) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    /**
     * 查找令牌
     *
     * @param token 令牌
     * @return {@link User}
     */
    @Select("select * from user_community where token = #{token}")
    User findByToken(@Param("token") String token);

    /**
     * 查找通过id
     *
     * @param creator 创造者
     * @return {@link User}
     */
    @Select("select * from user_community where id = #{creator}")
    User findById(@Param("creator") Integer creator);
}
