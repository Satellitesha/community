package peipeia.club.community.dto;

import lombok.Data;
import peipeia.club.community.model.User;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private  String notifierName;
    private String outerTitle;
    private Long outerid;
    private  String typeName;
    private  Integer type;
}
