package com.example.hrinterface.dao;

import com.example.hrinterface.entity.PersonalDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonalDocumentDAO {
    @Autowired
    protected SessionFactory sessionFactory;
    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public PersonalDocument findDocumentByID(int ID){
        Session session = getCurrentSession();
        Query query = session.createQuery("From PersonalDocument p WHERE p.id = :ID");
        query.setParameter("ID", ID);
        PersonalDocument document = (PersonalDocument) query.getSingleResult();
        return document;
    }

    public List<PersonalDocument> getDocsByEmployeeId(int id){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM PersonalDocument p WHERE p.employeeID = :id");
        query.setParameter("id", id);
        List<PersonalDocument> personalDocs = query.getResultList();
        return personalDocs;
    }
}
