package peipeia.club.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peipeia.club.community.mapper.UserMapper;
import peipeia.club.community.model.User;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
      User dbUser = userMapper.findByAccountId(user.getAccount_id());
      if (dbUser==null){
          //插入
          userMapper.insertUser(user);
          user.setGmt_create(System.currentTimeMillis());
          user.setGmt_modified(user.getGmt_create());
      }
      else {
          dbUser.setGmt_modified(System.currentTimeMillis());
          dbUser.setAvatar_url(user.getAvatar_url());
          dbUser.setName(user.getName());
          dbUser.setToken(user.getToken());
          userMapper.update(dbUser);
          //更新
      }
    }
}
