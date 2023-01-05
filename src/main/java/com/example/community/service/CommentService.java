package com.example.community.service;

import com.example.community.dto.CommentDto;
import com.example.community.enums.CommentTypeEnum;
import com.example.community.exception.CustomizeException;
import com.example.community.exception.ECustomizeErrorCode;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.model.Comment;
import com.example.community.model.Question;
import com.example.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 评论服务
 *
 * @author Tuoer
 * @date 2023/01/05
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public void insert(CommentDto commentDto, HttpSession session) {
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);
        //todo 完成id的获取
        User user1 = (User) session.getAttribute("user");
        String user = user1.getAccountId();
        comment.setCommentator(user);

        if (comment.getParentId() == null || Objects.equals(comment.getParentId(), "")) {
            throw new CustomizeException(ECustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(ECustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            // 回复评论
            Comment dbComment = commentMapper.selectById(Long.parseLong(comment.getParentId()));
            if (dbComment == null) {
                throw new CustomizeException(ECustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            // 回复问题
            Question question = questionMapper.selectById(Long.parseLong(comment.getParentId()));
            if (question == null) {
                throw new CustomizeException(ECustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            questionMapper.icComment(question.getId());
        }
    }
}

