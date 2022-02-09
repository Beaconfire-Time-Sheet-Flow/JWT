package com.example.springssoauthserver.dao;


import com.example.springssoauthserver.entity.Account;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AccountDAO extends AbstractHibernateDao<Account>{

    public AccountDAO(){
        setClazz(Account.class);
    }

    public Account findAccountById(Integer id){
        return findById(id);
    }

    public List<Account> getAccountsByUserName(String userName){
        Session session = getCurrentSession();
        Query getAccountsByUserName = session.createQuery("FROM Account a WHERE a.username = :username");
        getAccountsByUserName.setParameter("username", userName);
        List<Account> accounts = (List<Account>) getAccountsByUserName.getResultList();
        return accounts;
    }
}
