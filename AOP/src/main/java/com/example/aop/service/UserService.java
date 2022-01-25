package com.example.aop.service;

import com.example.aop.DAO.UserDAO;
import com.example.aop.domain.User;
import com.example.aop.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public List<User> findAll(){
        return userDAO.findAll();
    }

    public List<User> findByName(String name) throws UserNotFoundException {
        List<User> res = userDAO.findByName(name);
        if(res.isEmpty()){
            throw new UserNotFoundException(name);
        }
        return res;
    }
}
