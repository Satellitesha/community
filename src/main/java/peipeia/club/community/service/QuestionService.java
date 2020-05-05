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
        PageDTO pageDTO = new PageDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.count();

        if(totalCount % size==0){
            totalPage=totalCount / size;
        }else {
            totalPage=totalCount /size +1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        pageDTO.setPagination(totalPage,page);
        //5*(i-1)
        //把questions上的所有属性快速拷贝到questionDTO上
        Integer offset=size * (page-1);
        List<Question> questions = questionMapper.findQuestion(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
          User user=  userMapper.findById(question.getCreator());
          QuestionDTO questionDTO = new QuestionDTO();
          BeanUtils.copyProperties(question, questionDTO);
          questionDTO.setUser(user);
          questionDTOList.add(questionDTO);
        }
       pageDTO.setQuestion(questionDTOList);
        return pageDTO;
    }

    public PageDTO findQuestionById(Integer userId, Integer page, Integer size) {

        PageDTO pageDTO = new PageDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countById(userId);
        if(totalCount % size==0){
            totalPage=totalCount / size;
        }else {
            totalPage=totalCount /size +1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        pageDTO.setPagination(totalPage,page);
        //5*(i-1)
        Integer offset=size * (page-1);
        List<Question> questions = questionMapper.findQuestionById(userId,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user=  userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //把questions上的所有属性快速拷贝到questionDTO上
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageDTO.setQuestion(questionDTOList);
        return pageDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question= questionMapper.findById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user=  userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}

