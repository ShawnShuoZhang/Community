package com.example.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.community.dto.PaginationDto;
import com.example.community.model.Notification;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 通知映射器
 *
 * @author Tuoer
 * @date 2023/01/12
 */
@Mapper
public interface NotificationMapper extends BaseMapper<Notification> {
    /**
     * 通过用户id列表
     *
     * @param accountId 帐户id
     * @return {@link List}<{@link Notification}>
     */
    @Select("SELECT * FROM notification WHERE RECEIVER = #{accountId} ORDER BY gmt_create DESC")
    List<Notification> listByUserId(@Param("accountId") String accountId);


    /**
     * 插入
     *
     * @param notification 通知
     * @return int
     */
    @Insert("insert into notification (notifier, receiver, outer_id, type, gmt_create, status, notifier_name, outer_title) values (#{notifier}, #{receiver}, #{outerId}, #{type}, #{gmtCreate}, #{status}, #{notifierName}, #{outerTitle})")
    int insert(Notification notification);

    /**
     * 未读计数
     *
     * @param accountId 帐户id
     * @return {@link Long}
     */
    @Select("SELECT COUNT(1) FROM notification WHERE receiver = #{accountId} AND status = 0")
    Long unreadCount(@Param("accountId") String accountId);

    /**
     * 更新状态
     *
     * @param id id
     */
    @Update("UPDATE notification SET status = 1 WHERE id = #{id}")
    void updateStatus(@Param("id") Long id);
}