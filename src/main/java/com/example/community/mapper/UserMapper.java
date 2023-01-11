package com.example.community.mapper;

import com.example.community.model.User;
import org.apache.ibatis.annotations.*;

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
     * 找到了令牌
     * 查找令牌
     *
     * @param token 令牌
     * @return {@link User}
     */
    @Select("select * from user_community where token = #{token}")
    User findByToken(@Param("token") String token);

    // /**
    //  * 找到帐户id
    //  *
    //  * @param id id
    //  * @return {@link User}
    //  */
    // @Select("select * from user_community where ACCOUNT_ID = #{id}")
    // User findByAccountId(@Param("id")String id);

    /**
     * 发现通过id
     * 查找通过id
     *
     * @param creator 创造者
     * @return {@link User}
     */
    @Select("select * from user_community where ACCOUNT_ID = #{creator} limit 1")
    User findById(@Param("creator") String creator);

    /**
     * 更新
     *
     * @param byAccountId 通过帐户id
     */
    @Update("update user_community set token = #{token}, gmt_modified = #{gmtModified} where ACCOUNT_ID = #{accountId}")
    void update(User byAccountId);
}
