package peipeia.club.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import peipeia.club.community.dto.QuestionDTO;
import peipeia.club.community.mapper.QuestionMapper;
import peipeia.club.community.mapper.UserMapper;
import peipeia.club.community.model.Question;
import peipeia.club.community.model.User;
import peipeia.club.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @GetMapping("/")
    public  String index(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user=  userMapper.findByToken(token);
                    if (user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questionList =questionService.findQuestion();
        model.addAttribute("questions",questionList);
        return "index";
    }
}
