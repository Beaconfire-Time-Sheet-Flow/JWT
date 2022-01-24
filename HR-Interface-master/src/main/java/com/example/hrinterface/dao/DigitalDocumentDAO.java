package com.example.hrinterface.dao;

import com.example.hrinterface.entity.DigitalDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class DigitalDocumentDAO {

    @Autowired
    protected SessionFactory sessionFactory;
    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public DigitalDocument getDigitalDocById(Integer id){
        Session session = getCurrentSession();
        Query getDigitalDocById = session.createQuery("FROM DigitalDocument d WHERE id = :id");
        getDigitalDocById.setParameter("id", id);
        DigitalDocument digitalDocument = (DigitalDocument) getDigitalDocById.getSingleResult();
        return  digitalDocument;
    }

    //modify
    public void addNewDocument(DigitalDocument digitalDocument){
        getCurrentSession().saveOrUpdate(digitalDocument);
    }

    //modify
    public void updateDigitalDoc(DigitalDocument digitalDocument){
        getCurrentSession().saveOrUpdate(digitalDocument);
    }
}
