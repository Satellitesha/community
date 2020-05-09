package peipeia.club.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import peipeia.club.community.model.User;
@Data
@Component
public class QuestionDTO {
    private Long id;

    private String title;

    private String description;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer creator;

    private Integer commentCount;

    private Integer viewCount;

    private Integer likeCount;

    private String tag;
    private User user;
}
