package com.aop.forumspring.aspects;

import com.aop.forumspring.dto.CommDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class CommAspect {
    Logger LOG = Logger.getLogger("com.aop.forumspring");

    @Pointcut("execution(* com.aop.forumspring.service.CommService.addComment(com.aop.forumspring.dto.CommDTO)) && args(commDTO)")
    private void callAddComment(CommDTO commDTO) {}

    @Before(value = "callAddComment(commDTO)", argNames = "commDTO")
    public void initCallAddComment(CommDTO commDTO) {
        LOG.log(Level.INFO, "Context: addComment - " +
                "User with id: " + commDTO.getUser().getUserId() + " triggered an add comm event " +
                "for post with id: " + commDTO.getPostId());
    }

    @AfterReturning(pointcut = "callAddComment(commDTO)", argNames = "commDTO")
    public void callAddCommentSuccess(CommDTO commDTO) {
        LOG.log(Level.INFO, "Add comment successfully executed with details: " + commDTO.toString());
    }

    @AfterThrowing(pointcut = "callAddComment(commDTO)", throwing = "e", argNames = "commDTO, e")
    public void callAddCommentFailure(CommDTO commDTO, RuntimeException e) {
        LOG.log(Level.SEVERE, e.getMessage());
    }

    @Pointcut("execution(* com.aop.forumspring.service.CommService.getCommentsByPostId(int)) && args(postId)")
    private void callGetCommentsByPostId(int postId) {}

    @Before(value = "callGetCommentsByPostId(postId)", argNames = "postId")
    public void initCallGetCommentsByPostId(int postId) {
        LOG.log(Level.INFO, "Context: getCommentsByPostId - " +
                "Get all comments event triggered for post with id: " + postId);
    }

    @AfterReturning(pointcut = "callGetCommentsByPostId(postId)", returning = "comments", argNames = "postId, comments")
    public void callGetCommentsByPostIdSuccess(int postId, List<CommDTO> comments) {
        LOG.log(Level.INFO, "Get al comments for post with id: " + postId + " successfully executed; " +
                comments.size() + " comments retrieved");
    }

    @AfterThrowing(pointcut = "callGetCommentsByPostId(postId)", throwing = "e", argNames = "postId, e")
    public void callGetCommentsByPostIdFailure(int postId, RuntimeException e) {
        LOG.log(Level.SEVERE, e.getMessage());
    }
}
