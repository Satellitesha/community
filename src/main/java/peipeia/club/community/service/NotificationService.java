package peipeia.club.community.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peipeia.club.community.dto.NotificationDTO;
import peipeia.club.community.dto.PageDTO;
import peipeia.club.community.dto.QuestionDTO;
import peipeia.club.community.enums.NotificationStatusEnum;
import peipeia.club.community.enums.NotificationTypeEnum;
import peipeia.club.community.exception.CustomException;
import peipeia.club.community.exception.CustomizeErrorCodeImpl;
import peipeia.club.community.mapper.NotificationMapper;
import peipeia.club.community.mapper.UserMapper;
import peipeia.club.community.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService {
    @Autowired
    NotificationMapper notificationMapper;
    public PageDTO List(Long userId, Integer page, Integer size) {

        PageDTO<NotificationDTO> pageDTO = new PageDTO();
        Integer totalPage;
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount = (int)notificationMapper.countByExample(example);
        if(totalCount % size==0){
            totalPage=totalCount / size;
        }else {
            totalPage=totalCount /size +1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        pageDTO.setPagination(totalPage,page);
        //5*(i-1)
        Integer offset=size * (page-1);
        NotificationExample example1 = new NotificationExample();
        example1.createCriteria().andReceiverEqualTo(userId);
        example1.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, size));
        if (notifications.size()==0){
            return pageDTO;

        }
        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        pageDTO.setData(notificationDTOS);
        return pageDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return  notificationMapper.countByExample(example);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notifications = notificationMapper.selectByPrimaryKey(id);
        if (notifications==null){
            throw  new CustomException(CustomizeErrorCodeImpl.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notifications.getReceiver(),user.getId())){
            throw  new CustomException(CustomizeErrorCodeImpl.READ_NOTIFICATION_FAIL);

        }
        notifications.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notifications);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notifications,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notifications.getType()));
        return notificationDTO;
    }
}
