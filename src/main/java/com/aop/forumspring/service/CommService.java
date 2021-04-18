package com.aop.forumspring.service;

import com.aop.forumspring.dao.CommDAO;
import com.aop.forumspring.dao.UserDAO;
import com.aop.forumspring.dto.CommDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommService {

    @Autowired
    private CommDAO commDAO;

    @Autowired
    private UserService userService;

    public CommService() {}

    public void addComment(CommDTO commDTO) {
        commDAO.addComment(commDTO);
    }

    public List<CommDTO> getCommentsByPostId(int postId) {
        List<CommDTO> comments = commDAO.getCommentsByPostId(postId);
        for (CommDTO commDTO : comments) {
            commDTO.setUser(userService.getUserById(commDTO.getUser().getUserId()));
        }
        return comments;
    }
}
