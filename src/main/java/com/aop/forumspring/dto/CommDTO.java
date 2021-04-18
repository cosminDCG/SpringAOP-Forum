package com.aop.forumspring.dto;

import java.util.Date;

public class CommDTO {

    private int commId;
    private int postId;
    private UserDTO user;
    private String commText;
    private Date commDate;

    public int getCommId() {
        return commId;
    }

    public void setCommId(int commId) {
        this.commId = commId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCommText() {
        return commText;
    }

    public void setCommText(String commText) {
        this.commText = commText;
    }

    public Date getCommDate() {
        return commDate;
    }

    public void setCommDate(Date commDate) {
        this.commDate = commDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CommDTO{" +
                "postId=" + postId +
                ", user=" + user.getUserId() +
                ", commText='" + commText + '\'' +
                ", commDate=" + commDate +
                '}';
    }
}
