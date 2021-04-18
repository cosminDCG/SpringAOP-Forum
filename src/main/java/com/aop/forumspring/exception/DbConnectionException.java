package com.aop.forumspring.exception;

public class DbConnectionException extends RuntimeException {
    public DbConnectionException(String errorMessage) {
        super(errorMessage);
    }
}
