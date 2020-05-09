package peipeia.club.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peipeia.club.community.mapper.UserMapper;
import peipeia.club.community.model.User;
import peipeia.club.community.model.UserExample;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample =new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
      if (users.size()==0){
          //插入
          userMapper.insert(user);
          user.setGmtCreate(System.currentTimeMillis());
          user.setGmtModified(user.getGmtCreate());
      }
      else {
          User dbUser = users.get(0);
          User updateUser =new User();
          updateUser.setGmtModified(System.currentTimeMillis());
          updateUser.setAvatarUrl(user.getAvatarUrl());
          updateUser.setName(user.getName());
          updateUser.setToken(user.getToken());
            UserExample example =new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
          userMapper.updateByExampleSelective(updateUser,example);
          //更新
      }
    }
}
