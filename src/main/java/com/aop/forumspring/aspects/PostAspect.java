package com.aop.forumspring.aspects;

import com.aop.forumspring.dto.PostDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class PostAspect {
    Logger LOG = Logger.getLogger("com.aop.forumspring");

    @Pointcut("execution(* com.aop.forumspring.service.PostService.addPost(com.aop.forumspring.dto.PostDTO)) && args(postDTO)")
    private void callAddPost(PostDTO postDTO){}

    @Before(value = "callAddPost(postDTO)", argNames = "postDTO")
    public void initCallAddPost(PostDTO postDTO){
        LOG.log(Level.INFO, "Context: addPost - " +
                "User with id: " + postDTO.getUserId() + " triggered an add post event");
    }

    @AfterReturning(pointcut = "callAddPost(postDTO)", argNames = "postDTO")
    public void callAddPostSuccess(PostDTO postDTO) {
        LOG.log(Level.INFO, "Add post successfully executed with details: " + postDTO.toString());
    }

    @AfterThrowing(pointcut = "callAddPost(postDTO)", throwing = "e", argNames = "postDTO, e")
    public void callAddPostFailure(PostDTO postDTO, RuntimeException e) {
        LOG.log(Level.SEVERE, e.getMessage());
    }

    @Pointcut("execution(* com.aop.forumspring.service.PostService.getAllPosts())")
    private void callGetAllPosts(){}

    @Before(value = "callGetAllPosts()", argNames = "joinPoint")
    public void initCallGetAllPosts(JoinPoint joinPoint) {
        LOG.log(Level.INFO, "Context:" + joinPoint.getSignature() + " - " +
                "Get all posts triggered");
    }

    @AfterReturning(pointcut = "callGetAllPosts()", returning = "posts", argNames = "posts")
    public void callGetAllPostsSuccess(List<PostDTO> posts) {
        LOG.log(Level.INFO, "Get all posts successfully executed; " +
                posts.size() + " posts retrieved");
    }

    @AfterThrowing(pointcut = "callGetAllPosts()", throwing = "e", argNames = "e")
    public void callGetAllPostsFailure(RuntimeException e) {
        LOG.log(Level.SEVERE, e.getMessage());
    }
}
