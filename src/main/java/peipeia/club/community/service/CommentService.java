package peipeia.club.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peipeia.club.community.enums.CommentTypeEnum;
import peipeia.club.community.exception.CustomException;
import peipeia.club.community.exception.CustomizeErrorCode;
import peipeia.club.community.exception.CustomizeErrorCodeImpl;
import peipeia.club.community.mapper.CommentMapper;
import peipeia.club.community.mapper.QuestionExtMapper;
import peipeia.club.community.mapper.QuestionMapper;
import peipeia.club.community.model.Comment;
import peipeia.club.community.model.Question;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionExtMapper questionExtMapper;
    public void insertSelective(Comment comment) {
        if (comment.getParentId()==null || comment.getParentId()==0){
            throw  new CustomException(CustomizeErrorCodeImpl.TARGET_PARAM_FOUND);
        }
        if (comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomException(CustomizeErrorCodeImpl.TYPE_PARAM_WRONG);
        }
        if (comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment==null){
                throw new CustomException(CustomizeErrorCodeImpl.COMMENT_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
        }else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question==null){
                throw new CustomException(CustomizeErrorCodeImpl.QUESTION_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            question.setCommentCount(1);
            questionExtMapper.CommentCount(question);
        }
        }
}
