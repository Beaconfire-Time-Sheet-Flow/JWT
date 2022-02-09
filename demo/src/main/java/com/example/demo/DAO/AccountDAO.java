package com.example.demo.DAO;

import com.example.demo.entity.Account;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

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
