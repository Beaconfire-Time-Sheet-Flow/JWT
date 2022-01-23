package com.example.hrinterface.dao;

import com.example.hrinterface.entity.Address;
import com.example.hrinterface.entity.Person;
import com.example.hrinterface.entity.VisaStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisaStatusDAO {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public VisaStatus getVisaById(int ID){
        Session session = getCurrentSession();
        Query query = session.createQuery("From VisaStatus v WHERE v.ID = :ID");
        query.setParameter("ID", ID);
        VisaStatus visaStatus = (VisaStatus) query.getSingleResult();
        return visaStatus;
    }

    public VisaStatus getVisaByUserId(int ID){
        Session session = getCurrentSession();
        Query query = session.createQuery("From VisaStatus v WHERE v.createUser = :ID");
        query.setParameter("ID", ID);
        VisaStatus visaStatus = (VisaStatus) query.getSingleResult();
        return visaStatus;
    }

    public void updateVisaType(int ID, String visaType){
        Session session = getCurrentSession();
        session.beginTransaction();
        VisaStatus visaStatus = getVisaByUserId(ID);
        visaStatus.setVisaType(visaType);
        session.update(visaType);
        session.getTransaction().commit();
    }
}
