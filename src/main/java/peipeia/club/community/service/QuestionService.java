package peipeia.club.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
    QuestionDTO questionDTO;
    public List<QuestionDTO> findQuestion() {
        List<Question> questions = questionMapper.findQuestion();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
          User user=  userMapper.findById(question.getCreator());
          //把question1上的所有属性快速拷贝到questionDTO上
          BeanUtils.copyProperties(question,questionDTO);
          questionDTO.setUser(user);
          questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
