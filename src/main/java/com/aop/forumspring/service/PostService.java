package com.aop.forumspring.service;


import com.aop.forumspring.dao.PostDAO;
import com.aop.forumspring.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostDAO postDAO;

    private static final PostService postServiceInstance = new PostService();

    public PostService() {}

    public static PostService getInstance() {
        return postServiceInstance;
    }

    public void addPost(PostDTO postDTO) {
        postDAO.addPost(postDTO);
    }

    public List<PostDTO> getAllPosts() {
        return postDAO.getAllPosts();
    }

    public List<PostDTO> getPostsByUserId(int userId) {
        return postDAO.getPostsByUserId(userId);
    }
}
