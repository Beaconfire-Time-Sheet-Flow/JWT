package com.example.hrinterface.dao;

import com.example.hrinterface.entity.Person;
import com.example.hrinterface.entity.RegistrationToken;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class RegistrationTokenDAO {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public RegistrationToken findTokenByToken(String token){
        Session session = getCurrentSession();
        Query query = session.createQuery("From RegistrationToken t WHERE t.token = :token");
        query.setParameter("token", token);
        RegistrationToken registrationToken = (RegistrationToken) query.getSingleResult();
        return registrationToken;
    }

    public void createToken(RegistrationToken token){
        Session session = getCurrentSession();
        Serializable id = session.save(token);
        System.out.println(id);
    }
}
