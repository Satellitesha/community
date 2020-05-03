package peipeia.club.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peipeia.club.community.dto.PageDTO;
import peipeia.club.community.dto.QuestionDTO;
import peipeia.club.community.mapper.QuestionMapper;
import peipeia.club.community.mapper.UserMapper;
import peipeia.club.community.model.Question;
import peipeia.club.community.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;
    public PageDTO findQuestion(Integer page, Integer size) {
        QuestionDTO questionDTO = new QuestionDTO();
        PageDTO pageDTO = new PageDTO();
        Integer totalCount = questionMapper.count();
        pageDTO.setPagination(totalCount,page,size);

        if (page<1){
            page=1;
        }
        if (page>pageDTO.getTotalPage()){
            page=pageDTO.getTotalPage() ;
        }
        //5*(i-1)
        Integer offset=size * (page-1);
        List<Question> questions = questionMapper.findQuestion(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
          User user=  userMapper.findById(question.getCreator());
          //把questions上的所有属性快速拷贝到questionDTO上
          BeanUtils.copyProperties(question, questionDTO);
          questionDTO.setUser(user);
          questionDTOList.add(questionDTO);
        }
       pageDTO.setQuestion(questionDTOList);
        return pageDTO;
    }
}
