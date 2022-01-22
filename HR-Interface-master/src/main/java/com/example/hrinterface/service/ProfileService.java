package com.example.hrinterface.service;

import com.example.hrinterface.dao.AddressDAO;
import com.example.hrinterface.dao.ContactDAO;
import com.example.hrinterface.dao.EmployeeDAO;
import com.example.hrinterface.dao.PersonDAO;
import com.example.hrinterface.entity.Address;
import com.example.hrinterface.entity.Contact;
import com.example.hrinterface.entity.Employee;
import com.example.hrinterface.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    PersonDAO personDAO;
    @Autowired
    ContactDAO contactDAO;
    @Autowired
    AddressDAO addressDAO;

    public List<Employee> getAllEmployee(){
        try {
            return employeeDAO.getAllEmployee();
        }catch (Exception E){
            System.out.println(E);
        }
        return null;
    }

    public Employee findEmployeeByID(int ID){
        try {
            return employeeDAO.findEmployeeByID(ID);
        }
        catch (Exception E){
            System.out.println(E);
        }
        return null;
    }

    public Person findPersonByID(int ID){
        try {
            return personDAO.findPersonByID(ID);
        }
        catch (Exception E){
            System.out.println(E);
        }
        return null;
    }

    public Contact getContactById(int ID){
        try {
            return contactDAO.getContactByPersonId(ID);
        }
        catch (Exception E){
            System.out.println(E);
        }
        return null;
    }

    public List<Address> getAddressByPersonId(int ID){
        try {
            return addressDAO.getAddressByPersonId(ID);
        }
        catch (Exception E){
            System.out.println(E);
        }
        return null;}

    public void updateVisaInfo(int id, String name, String startDate, String endDate){
        String[] names = name.split(" ");
        if(names.length == 2) {
            try {
                personDAO.updateName(id, names);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        try {
            employeeDAO.updateVisaInfo(id, startDate, endDate);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public int createPerson(Person person){
        try{
           return personDAO.createPerson(person);
        }catch (Exception e){
            System.out.println(e);
        }
        return -1;
    }
}
