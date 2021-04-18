package com.aop.forumspring.dto;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PostDTO {

    private int postId;
    private int userId;
    private String postText;
    private Date postDate;
    private List<CommDTO> comments;

    public List<CommDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommDTO> comments) {
        this.comments = comments;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "userId=" + userId +
                ", postText='" + postText + '\'' +
                ", postDate=" + postDate +
                '}';
    }
}
