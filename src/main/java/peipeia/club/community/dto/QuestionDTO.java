package peipeia.club.community.dto;

import org.springframework.stereotype.Component;
import peipeia.club.community.model.User;
@Component
public class QuestionDTO {
    private  Long id;
    private  String title;
    private  String description;
    private  Long gmt_create;
    private  Long gmt_modified;
    private  Integer creator;
    private  Integer comment_count;
    private  Integer view_count;
    private  Integer like_count;
    private  String tag;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Long getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Long gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", gmt_create=" + gmt_create +
                ", gmt_modified=" + gmt_modified +
                ", creator=" + creator +
                ", comment_count=" + comment_count +
                ", view_count=" + view_count +
                ", like_count=" + like_count +
                ", tag='" + tag + '\'' +
                ", user=" + user +
                '}';
    }
}
