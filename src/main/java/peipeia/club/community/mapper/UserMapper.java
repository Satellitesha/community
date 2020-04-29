package peipeia.club.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import peipeia.club.community.model.User;

@Mapper
public interface UserMapper {
    @Insert("insert into USER(name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{bio},#{avatar_url}) ")
    void insertUser(User user);
    @Select("select * from USER where token=#{token}")
    User findByToken(String token);
    @Select("select * from USER where id=#{id}")
    User findById(Integer creator);
}
