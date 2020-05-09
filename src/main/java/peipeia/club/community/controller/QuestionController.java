package peipeia.club.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import peipeia.club.community.dto.QuestionDTO;
import peipeia.club.community.mapper.QuestionMapper;
import peipeia.club.community.model.Question;
import peipeia.club.community.service.QuestionService;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        //增加累计阅读数
        questionService.inView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
