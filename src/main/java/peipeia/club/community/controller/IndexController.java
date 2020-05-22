package peipeia.club.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import peipeia.club.community.dto.PageDTO;
import peipeia.club.community.service.QuestionService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class IndexController {

    @Autowired
    QuestionService questionService;
    @GetMapping("/")
    public  String index(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model,
                         @RequestParam(name="page",defaultValue = "1") Integer page,
                         @RequestParam(name="size",defaultValue = "5") Integer size,
                         @RequestParam(name = "search",required = false) String search) {
        PageDTO  pagination=questionService.findQuestion(search,page,size);
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        return "index";
    }
}
