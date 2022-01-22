package com.example.hrinterface.dao;

import com.example.hrinterface.entity.Contact;
import com.example.hrinterface.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAO {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public Contact getContactByPersonId(int ID){
        Session session = getCurrentSession();
        Query query = session.createQuery("From Contact c WHERE c.personID = :ID");
        query.setParameter("ID", ID);
        Contact contact = (Contact) query.getSingleResult();
        return contact;
    }
}
