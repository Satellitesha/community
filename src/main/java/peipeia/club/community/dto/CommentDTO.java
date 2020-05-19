package peipeia.club.community.dto;

import lombok.Data;
import peipeia.club.community.model.User;
@Data
public class CommentDTO {
        private Long id;

        private Long parentId;

        private Integer type;

        private String content;

        private Long commentator;

        private Long gmtCreate;

        private Long gmtModified;

        private Integer likeCount;
        private User user;
        private  Integer commentCount;


}
