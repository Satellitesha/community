package peipeia.club.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peipeia.club.community.dto.CommentCreateDTO;
import peipeia.club.community.dto.CommentDTO;
import peipeia.club.community.dto.ResultDTO;
import peipeia.club.community.enums.CommentTypeEnum;
import peipeia.club.community.exception.CustomizeErrorCodeImpl;
import peipeia.club.community.model.Comment;
import peipeia.club.community.model.User;
import peipeia.club.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
    public  Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                        HttpServletRequest request){
        //从session中获取user
       User user = (User) request.getSession().getAttribute("user");
       if (user==null){
           //异常处理
          return ResultDTO.errorOf(CustomizeErrorCodeImpl.NO_LOGIN);
       }
       if (commentCreateDTO==null || StringUtils.isBlank(commentCreateDTO.getContent())){
           return  ResultDTO.errorOf(CustomizeErrorCodeImpl.COMMENT_IS_EMPTY);
       }
       //从浏览器获取到commentDTO的值，再存入comment对象中
        Comment comment =new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        //调用service方法传入comment对象
        commentService.insertSelective(comment,user);
        Map<Object,Object> objectObjectMap=new HashMap<>();
        objectObjectMap.put("message","成功");
        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public  ResultDTO<List> comments(@PathVariable(name = "id") Long id){
       List<CommentDTO> commentDTOS= commentService.listByTargetId(id,CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
