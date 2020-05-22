package peipeia.club.community.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import peipeia.club.community.dto.PageDTO;
import peipeia.club.community.model.User;
import peipeia.club.community.service.CommentService;
import peipeia.club.community.service.NotificationService;
import peipeia.club.community.service.QuestionService;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class ProfileController {
    @Autowired
    QuestionService questionService;
    @Autowired
    NotificationService notificationService;
    @GetMapping("/profile/{action}")
    public  String profile(@PathVariable(name = "action")String action,
                           Model model,
                           HttpServletRequest request,
                           @RequestParam(name="page",defaultValue = "1") Integer page,
                           @RequestParam(name="size",defaultValue = "5") Integer size){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            log.error("profile error {}",user);
            return "redirect:/";
        }
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PageDTO pageDTO = questionService.findQuestionById(user.getId(), page, size);
            model.addAttribute("pagination",pageDTO);
        }else  if("replies".equals(action)){
            PageDTO pageDTO=notificationService.List(user.getId(),page,size);
            model.addAttribute("section","replies");
            model.addAttribute("pagination",pageDTO);
            model.addAttribute("sectionName","最新回复");
        }

        return "profile";
    }
}
