package com.example.community.service;

import com.example.community.dto.CommentCreateDto;
import com.example.community.dto.CommentDto;
import com.example.community.enums.CommentTypeEnum;
import com.example.community.exception.CustomizeException;
import com.example.community.exception.ECustomizeErrorCode;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Comment;
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

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(CommentCreateDto commentCreateDto, HttpSession session) {
        Comment comment = new Comment();
        comment.setParentId(commentCreateDto.getParentId());
        comment.setContent(commentCreateDto.getContent());
        comment.setType(commentCreateDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);
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

    /**
     * 发现由父id
     *
     * @param questionId 问题id
     * @return {@link List}<{@link CommentCreateDto}>
     */
    public List<CommentDto> findByParentId(Long questionId) {
        System.out.println("questionId = " + questionId.toString());
        List<Comment> byParentId = commentMapper.selectByParentIdAndType(String.valueOf(questionId), CommentTypeEnum.QUESTION.getType());
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
        return commentDtos;
    }
}

