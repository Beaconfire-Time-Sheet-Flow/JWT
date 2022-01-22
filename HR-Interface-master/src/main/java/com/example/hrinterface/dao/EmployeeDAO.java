package com.example.hrinterface.dao;

import com.example.hrinterface.entity.Employee;
import com.example.hrinterface.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<Employee> getAllEmployee(){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Employee ");
        List<Employee> employeeList = query.getResultList();
        return employeeList;
    }

    public Employee findEmployeeByID(int ID){
        Session session = getCurrentSession();
        Query query = session.createQuery("From Employee e WHERE e.id = :ID");
        query.setParameter("ID", ID);
        Employee employee = (Employee) query.getSingleResult();
        return employee;
    }

    public void updateVisaInfo(int id, String startDate, String endDate){
        Session session = getCurrentSession();
        session.beginTransaction();
        Employee employee = findEmployeeByID(id);
        employee.setVisaStartDate(startDate);
        employee.setVisaEndDate(endDate);
        session.update(employee);
        session.getTransaction().commit();
    }
}
