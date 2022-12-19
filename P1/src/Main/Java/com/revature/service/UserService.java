package com.revature.service;

import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UserAlreadyExistException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.persistence.UserDao;
import com.revature.pojos.User;

import java.util.Set;

public class UserService {
    private UserDao dao;
    public UserService(UserDao dao){
        this.dao = dao;
    }

    public void registerNewUser(User user) throws UserAlreadyExistException {
        dao.create(user);
    }

    public User getUser(User user) throws UserNotFoundException, PasswordIncorrectException {
        return dao.authenticate(user.getEmail(), user.getPassword());
    }
    /*
    public void updateUser(User user){
        dao.update(user);
    }*/
    /*public void deleteUser(User user){
        dao.delete(user);
    }*/
    /*
    public Set<User> getAllUsers(){
        return dao.getAllUsers();
    }*/
}
