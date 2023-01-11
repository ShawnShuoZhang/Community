package com.example.community.service;

import cn.hutool.core.collection.ListUtil;
import com.example.community.dto.CommentCreateDto;
import com.example.community.dto.CommentDto;
import com.example.community.enums.CommentTypeEnum;
import com.example.community.enums.CustomizeErrorCode;
import com.example.community.enums.NotificationEnum;
import com.example.community.enums.NotificationStatusEnum;
import com.example.community.exception.CustomizeException;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.NotificationMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Comment;
import com.example.community.model.Notification;
import com.example.community.model.Question;
import com.example.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 评论服务
 *
 * @author Tuoer
 * @date 2023/01/05
 */
@Service
public class CommentService {

    /**
     * 评论映射器
     */
    @Autowired
    private CommentMapper commentMapper;

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
    /**
     * 通知映射器
     */
    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 插入
     *
     * @param commentCreateDto 评论创建dto
     * @param session          会话
     * @param type             类型
     */
    @Transactional
    public void insert(CommentCreateDto commentCreateDto, HttpSession session, Integer type) {
        Comment comment = new Comment();
        comment.setParentId(commentCreateDto.getParentId());
        comment.setContent(commentCreateDto.getContent());
        comment.setType(commentCreateDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);
        User user1 = (User) session.getAttribute("user");
        System.out.println(user1.getName());
        String user = user1.getAccountId();
        comment.setCommentator(user);

        if (comment.getParentId() == null || Objects.equals(comment.getParentId(), "")) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType().equals(type)) {
            // 回复评论
            Comment dbComment = commentMapper.selectById(Long.parseLong(comment.getParentId()));
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            Question question = questionMapper.selectById(Long.parseLong(dbComment.getParentId()));
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            commentMapper.incCommentCount(Long.parseLong(comment.getParentId()));
            createNotify(comment, dbComment.getCommentator(), NotificationEnum.REPLY_COMMENT, user1.getName(), question.getTitle(), question.getId().toString());
        } else {
            // 回复问题
            Question question = questionMapper.selectById(Long.parseLong(comment.getParentId()));
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            questionMapper.icComment(question.getId());
            createNotify(comment, question.getCreator(), NotificationEnum.REPLY_QUESTION, user1.getName(), question.getTitle(), question.getId().toString());
        }
    }

    /**
     * 创建通知
     *
     * @param comment          评论
     * @param receiver         接收机
     * @param notificationEnum 通知枚举
     * @param notifierName     通知人名称
     * @param outerTitle       外标题
     * @param outerId          外部id
     */
    private void createNotify(Comment comment, String receiver, NotificationEnum notificationEnum, String notifierName, String outerTitle, String outerId) {
        Notification entity = new Notification();
        entity.setGmtCreate(System.currentTimeMillis());
        entity.setType(notificationEnum.getType());
        entity.setOuterId(outerId);
        entity.setNotifier(comment.getCommentator());
        entity.setType(notificationEnum.getType());
        entity.setReceiver(receiver);
        entity.setNotifierName(notifierName);
        entity.setOuterTitle(outerTitle);
        entity.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notificationMapper.insert(entity);
    }

    /**
     * 发现由父id
     *
     * @param questionId 问题id
     * @param type       类型
     * @return {@link List}<{@link CommentDto}>
     */
    public List<CommentDto> findByParentId(Long questionId, CommentTypeEnum type) {
        System.out.println("questionId = " + questionId.toString());
        List<Comment> byParentId = commentMapper.selectByParentIdAndType(String.valueOf(questionId), type.getType());
        if (byParentId.size() == 0) {
            return new ArrayList<>();
        }
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : byParentId) {
            CommentDto commentDto = new CommentDto();
            commentDto.setUser(userMapper.findById(comment.getCommentator()));
            BeanUtils.copyProperties(comment, commentDto);
            commentDtos.add(commentDto);
        }
        ListUtil.sortByProperty(commentDtos, "gmtCreate");
        ListUtil.reverse(commentDtos);
        return commentDtos;
    }
}

