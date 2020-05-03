package peipeia.club.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import peipeia.club.community.model.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,creator,gmt_create,gmt_modified,tag) values (#{title},#{description},#{creator},#{gmt_create},#{gmt_modified},#{tag})")
    void questionInsert(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> findQuestion(@Param(value = "offset") Integer offset, Integer size);
    @Select("select count(1) from question")
    Integer count();
}
