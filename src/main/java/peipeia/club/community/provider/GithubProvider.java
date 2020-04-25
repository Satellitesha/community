package peipeia.club.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Component;
import peipeia.club.community.dto.AccessTokenDTO;
import peipeia.club.community.dto.GithubUser;

/**
 * Created by codedrinker on 2019/4/24.
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token?grant_type=authorization_code&code="+accessTokenDTO.getCode()
                        +"&client_id="+accessTokenDTO.getClient_id()+"&redirect_uri="+accessTokenDTO.getRedirect_uri()+"&client_secret="+accessTokenDTO.getClient_secret())
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str= response.body().string();
            // 返回的是下面这样一个字符串，所以先把它给转换成json对象，然后获取它里面对应的token
            // {"access_token":"783e1f8ec832e301ee9c831e201396a8","token_type":"bearer","expires_in":86400,"refresh_token":"5940b861e2bab8e2d2ffba74ebf320ede12af85245c4011c528e12a614cecb29","scope":"user_info","created_at":1585487419}
            JSONObject obj=JSONObject.parseObject(str);
            return obj.get("access_token").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (Exception e) {
        }
        return null;
    }
}


