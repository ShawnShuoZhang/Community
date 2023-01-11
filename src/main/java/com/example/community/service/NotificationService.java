package com.example.community.service;

import com.example.community.dto.NotificationDto;
import com.example.community.dto.PaginationDto;
import com.example.community.enums.CustomizeErrorCode;
import com.example.community.enums.NotificationEnum;
import com.example.community.exception.CustomizeException;
import com.example.community.mapper.NotificationMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Notification;
import com.example.community.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 通知服务
 *
 * @author Tuoer
 * @date 2023/01/11
 */
@Service
public class NotificationService {

    /**
     * 通知映射器
     */
    @Autowired
    private NotificationMapper notificationMapper;
    /**
     * 用户映射器
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 列表
     *
     * @param accountId 帐户id
     * @param page      页面
     * @param size      大小
     * @return {@link PaginationDto}<{@link NotificationDto}>
     */
    public PaginationDto<NotificationDto> list(String accountId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Notification> notifications = notificationMapper.listByUserId(accountId);
        return getPaginationDto(page, size, notifications);
    }

    /**
     * 得到分页dto
     *
     * @param page 页面
     * @param size 大小
     * @param list 列表
     * @return {@link PaginationDto}<{@link NotificationDto}>
     */
    private PaginationDto<NotificationDto> getPaginationDto(Integer page, Integer size, List<Notification> list) {
        PaginationDto<NotificationDto> paginationDto = new PaginationDto<>();
        List<NotificationDto> notificationDtos = new ArrayList<>();
        for (Notification notification : list) {
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);
            notificationDto.setTypeName(NotificationEnum.nameOfType(notification.getType()));
            notificationDtos.add(notificationDto);
        }
        paginationDto.setData(notificationDtos);
        PageInfo<Notification> pageInfo = new PageInfo<>(list);
        int pages = pageInfo.getPages();
        long total = pageInfo.getTotal();
        paginationDto.setPagination(pages, total, page, size);
        return paginationDto;
    }

    /**
     * 未读计数
     *
     * @param accountId 帐户id
     * @return {@link Long}
     */
    public Long unreadCount(String accountId) {
        return notificationMapper.unreadCount(accountId);
    }

    /**
     * 读
     *
     * @param id   id
     * @param user 用户
     * @return {@link NotificationDto}
     */
    public NotificationDto read(Long id, User user) {
        Notification notification = notificationMapper.selectById(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getAccountId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        NotificationDto notificationDto = new NotificationDto();
        BeanUtils.copyProperties(notification, notificationDto);
        notificationDto.setTypeName(NotificationEnum.nameOfType(notification.getType()));
        notificationMapper.updateStatus(id);
        return notificationDto;
    }
}
