package com.aop.forumspring.service;

import com.aop.forumspring.dao.UserDAO;
import com.aop.forumspring.dto.UserDTO;
import com.aop.forumspring.exception.EmailAlreadyExistsException;
import com.aop.forumspring.exception.ErrorMessages;
import com.aop.forumspring.exception.UserNotFoundException;
import com.aop.forumspring.exception.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    private static final UserService userServiceInstance = new UserService();

    public UserService(){}

    public static UserService getInstance() {
        return userServiceInstance;
    }

    public UserDTO getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public UserDTO getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public void addUser(UserDTO userDTO) {
        userDAO.addUser(userDTO);
    }

    public String registerUser(UserDTO userDTO) {
        UserDTO user = getUserByEmail(userDTO.getEmail());
        if(user != null) {
            throw new EmailAlreadyExistsException(ErrorMessages.EMAIL_ALREADY_EXISTS.getMessage() + userDTO.getEmail());
        }
        userDAO.addUser(userDTO);
        return "Success";
    }

    public UserDTO logIn(String email, String password) {
        UserDTO user = getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(ErrorMessages.USER_NOT_FOUND.getMessage());
        } else if(user.getPassword().equals(password)) {
            return user;
        } else {
            throw new WrongPasswordException(ErrorMessages.WRONG_PASSWORD.getMessage() + email);
        }
    }

    public int deleteUser(String email) {
        userDAO.deleteUser(email);
        return 1;
    }
}
