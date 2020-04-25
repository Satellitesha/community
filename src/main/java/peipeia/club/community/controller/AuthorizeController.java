package peipeia.club.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import peipeia.club.community.dto.AccessTokenDTO;
import peipeia.club.community.dto.GithubUser;
import peipeia.club.community.provider.GithubProvider;

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
    @GetMapping("/callback")
    public String callback(@RequestParam(value="code",required = false) String code){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        System.out.println(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }

}

