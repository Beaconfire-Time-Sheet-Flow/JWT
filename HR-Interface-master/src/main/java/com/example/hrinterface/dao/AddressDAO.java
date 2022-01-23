package com.example.hrinterface.dao;

import com.example.hrinterface.entity.Address;
import com.example.hrinterface.entity.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AddressDAO {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<Address> getAddressByPersonId(int ID){
        Session session = getCurrentSession();
        Query query = session.createQuery("From Address a WHERE a.personID = :ID");
        query.setParameter("ID", ID);
        List<Address> addressList =query.getResultList();
        return addressList;
    }
}
