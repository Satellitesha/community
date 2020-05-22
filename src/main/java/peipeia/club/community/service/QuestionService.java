package peipeia.club.community.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peipeia.club.community.dto.PageDTO;
import peipeia.club.community.dto.QuestionDTO;
import peipeia.club.community.dto.QuestionQueryDTO;
import peipeia.club.community.exception.CustomException;
import peipeia.club.community.exception.CustomizeErrorCodeImpl;
import peipeia.club.community.mapper.QuestionExtMapper;
import peipeia.club.community.mapper.QuestionMapper;
import peipeia.club.community.mapper.UserMapper;
import peipeia.club.community.model.Question;
import peipeia.club.community.model.QuestionExample;
import peipeia.club.community.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionExtMapper questionExtMapper;
    public PageDTO findQuestion(String search, Integer page, Integer size) {
        if (StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search," ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
            System.out.println(search);
        }
        PageDTO pageDTO = new PageDTO();
        Integer totalPage;
        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);
        Integer totalCount = questionExtMapper.countBySearch(questionQueryDTO);

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
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        questionQueryDTO.setPage(offset);
        questionQueryDTO.setSize(size);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
          User user=  userMapper.selectByPrimaryKey(question.getCreator());
          QuestionDTO questionDTO = new QuestionDTO();
          BeanUtils.copyProperties(question, questionDTO);
          questionDTO.setUser(user);
          questionDTOList.add(questionDTO);
        }
       pageDTO.setData(questionDTOList);
        return pageDTO;
    }

    public PageDTO findQuestionById(Long userId, Integer page, Integer size) {

        PageDTO pageDTO = new PageDTO();
        Integer totalPage;
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(example);
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
        QuestionExample example1 = new QuestionExample();
        example1.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user=  userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //把questions上的所有属性快速拷贝到questionDTO上
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageDTO.setData(questionDTOList);
        return pageDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomException(CustomizeErrorCodeImpl.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void creatOrUpdate(Question question) {
        if (question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertSelective(question);
        }else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int i = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (i!=1){
                throw  new CustomException(CustomizeErrorCodeImpl.QUESTION_NOT_FOUND);
            }
        }
    }

    public void inView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.inView(question);
    }
//    标签
    public List<QuestionDTO> selectByRelated(QuestionDTO queryDTO) {
        //判断tag是否为空
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return  new ArrayList<>();
        }
        //将查到的tag以逗号分开
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        //吧tag以tag1|tag2|tag3
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question=new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);
        List<Question> questions = questionExtMapper.selectByRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());

        return questionDTOS;
    }
}

