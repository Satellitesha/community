package peipeia.club.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import peipeia.club.community.model.User;
@Data
@Component
public class QuestionDTO {
    private  Long id;
    private  String title;
    private  String description;
    private  Long gmt_create;
    private  Long gmt_modified;
    private  String creator;
    private  Integer comment_count;
    private  Integer view_count;
    private  Integer like_count;
    private  String tag;
    private User user;
}
