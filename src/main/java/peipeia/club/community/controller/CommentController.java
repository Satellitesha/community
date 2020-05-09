package peipeia.club.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peipeia.club.community.dto.CommentDTO;
import peipeia.club.community.dto.ResultDTO;
import peipeia.club.community.exception.CustomizeErrorCodeImpl;
import peipeia.club.community.model.Comment;
import peipeia.club.community.model.User;
import peipeia.club.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 回复功能实现
 *
 */
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;
    @RequestMapping(value = "/comment",method = RequestMethod.POST )
    public  Object post(@RequestBody  CommentDTO commentDTO,
                        HttpServletRequest request){
       User user = (User) request.getSession().getAttribute("user");
       if (user==null){
          return ResultDTO.errorOf(CustomizeErrorCodeImpl.NO_LOGIN);
       }
        Comment comment =new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        commentService.insertSelective(comment);
        Map<Object,Object> objectObjectMap=new HashMap<>();
        objectObjectMap.put("message","成功");
        return ResultDTO.okOf();
    }
}
