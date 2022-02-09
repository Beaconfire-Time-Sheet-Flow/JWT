package com.example.springssoauthserver.service;


import com.example.springssoauthserver.dao.AccountDAO;
import com.example.springssoauthserver.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public List<Account> checkLogin(String username,String password){
        List<Account> res = accountDAO.getAccountsByUserName(username);
        if(res.size() == 0)
            return null;
        if(res.get(0).getPassword().equals(password))
            return res;
        else
            return null;
    }
}
