package com.example.hrinterface.dao;

import com.example.hrinterface.entity.Address;
import com.example.hrinterface.entity.Person;
import com.example.hrinterface.entity.UserRole;
import com.example.hrinterface.entity.VisaStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleDAO {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public UserRole findUserRoleByID(int ID){
        Session session = getCurrentSession();
        Query query = session.createQuery("From UserRole r WHERE r.id = :ID");
        query.setParameter("ID", ID);
        UserRole userRole = (UserRole) query.getSingleResult();
        return userRole;
    }

    public List<UserRole> getRegList(){
        Session session = getCurrentSession();
        Query query = session.createQuery("From UserRole");
        List<UserRole> userRoleList =query.getResultList();
        return userRoleList;
    }

    public void updateUserRole(UserRole userRole){
        Session session = getCurrentSession();
        session.beginTransaction();
        session.update(userRole);
        session.getTransaction().commit();
    }
}
