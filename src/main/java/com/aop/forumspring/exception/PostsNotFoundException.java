package com.aop.forumspring.exception;

public class PostsNotFoundException extends RuntimeException {
    public PostsNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
