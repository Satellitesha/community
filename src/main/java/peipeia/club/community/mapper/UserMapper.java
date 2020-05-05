package peipeia.club.community.mapper;

import org.apache.ibatis.annotations.*;
import peipeia.club.community.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into USER(name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{bio},#{avatar_url}) ")
    void insertUser(User user);
    @Select("select * from USER where token=#{token}")
    User findByToken(String token);
    @Select("select * from USER where id=#{id}")
    User findById(@Param("id") Integer id);
    @Select("select * from USER where account_id=#{account_id}")
    User findByAccountId(@Param("account_id") String account_id);
    @Update("update USER set name=#{name},token=#{token},gmt_modified=#{gmt_modified},avatar_url=#{avatar_url} where id=#{id}")
    void update(User user);
}
