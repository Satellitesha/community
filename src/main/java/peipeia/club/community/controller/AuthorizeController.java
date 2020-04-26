package peipeia.club.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import peipeia.club.community.dto.AccessTokenDTO;
import peipeia.club.community.dto.GithubUser;
import peipeia.club.community.mapper.UserMapper;
import peipeia.club.community.model.User;
import peipeia.club.community.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 *
 */


@Controller
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
    UserMapper userMapper;
    @Autowired
    User user;
    @GetMapping("/callback")
    public String callback(@RequestParam(value="code",required = false) String code,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        System.out.println(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser= githubProvider.getUser(accessToken);
        if (githubUser!=null){
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insertUser(user);
            //登录成功，写cookie和session
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else {
            //登录重新登录
            return "redirect:/";
        }
    }

}

