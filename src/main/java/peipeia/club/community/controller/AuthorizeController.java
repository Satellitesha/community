package peipeia.club.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import peipeia.club.community.dto.AccessTokenDTO;
import peipeia.club.community.dto.GithubUser;
import peipeia.club.community.model.User;
import peipeia.club.community.provider.GithubProvider;
import peipeia.club.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 *第三方码云登录
 */


@Controller
@Slf4j
public class AuthorizeController {
    @Value("${MaYun.client.id}")
    private  String clientId;
    @Value("${MaYun.client.secret}")
    private  String clientSecret;
    @Value("${MaYun.redirect.uri}")
    private  String redirectUri;
    @Autowired
    GithubProvider githubProvider;
    @Autowired
    UserService userService;
    @GetMapping("/callback")
    public String callback(@RequestParam(value="code",required = false) String code,
                           HttpServletRequest request,
                           HttpServletResponse response){
        //把从前端获取到的code和个人的clientId、clientSecret、redirectUri储存到accesstoken中
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser= githubProvider.getUser(accessToken);
        //如果githubUser不为空吧数据存储到User对象中
        if (githubUser!=null && githubUser.getId()!=null){
            User user=new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.createOrUpdate(user);
            //用户登录成功后获取到唯一token，将token存入cookie
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        }else {
            //登录重新登录
            log.error("callback MaYunError {}",githubUser);
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    public  String logOut(HttpServletRequest request,
                          HttpServletResponse response){
        //清除session
        request.getSession().removeAttribute("user");
        //清除cookie
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}

