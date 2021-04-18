package com.aop.forumspring.aspects;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import com.aop.forumspring.dto.UserDTO;
import com.aop.forumspring.service.UserService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class UserAspect {
    Logger LOG = Logger.getLogger("com.aop.forumspring");

    @Pointcut("execution(* com.aop.forumspring.service.UserService.logIn(String, String)) && args(email, ..)")
    private void callLogin(String email) {}

    @Before(value = "callLogin(email)", argNames = "email")
    public void initCallLogin(String email) {
        LOG.log(Level.INFO, "Login method triggered for email " + email);
    }

    @AfterReturning(pointcut = "callLogin(email)", returning = "userDTO", argNames = "email, userDTO")
    public void callLoginSuccess(String email, UserDTO userDTO) {
        LOG.log(Level.INFO, "Login succeeded for email " + email +
                " with details id: " + userDTO.getUserId()  +
                "; name: " + userDTO.getFirstName() + " " +userDTO.getLastName());
    }

    @AfterThrowing(pointcut = "callLogin(email)", throwing = "e", argNames = "email, e")
    public void callLoginFailure(String email, RuntimeException e) {
        LOG.log(Level.SEVERE, e.getMessage());
    }

    @Pointcut("execution(* com.aop.forumspring.service.UserService.registerUser(com.aop.forumspring.dto.UserDTO)) && args(userDTO)")
    private void callRegister(UserDTO userDTO){}

    @Before(value = "callRegister(userDTO)", argNames = "userDTO")
    public void initCallRegister(UserDTO userDTO) {
        LOG.log(Level.INFO, "Register triggered with the following details: " + userDTO.toString());
    }

    @AfterReturning(pointcut = "callRegister(userDTO)", returning = "message", argNames = "userDTO, message")
    public void callRegisterSuccess(UserDTO userDTO, String message) {
        LOG.log(Level.INFO, "Register finished with status " + message + " for email " + userDTO.getEmail());
    }

    @AfterThrowing(pointcut = "callRegister(userDTO)", throwing = "e", argNames = "userDTO, e")
    public void callRegisterFailure(UserDTO userDTO, RuntimeException e) {
        LOG.log(Level.SEVERE, e.getMessage());
    }

}
