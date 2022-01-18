package com.example.team1.service;

import com.example.team1.DAO.Dao.EmployeeDao;
import com.example.team1.DAO.Dao.PersonDao;
import com.example.team1.domain.ContactDomain;
import com.example.team1.domain.PersonDomain;
import com.example.team1.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public Person findPersonById(Integer id){
        Optional<Person> optional = Optional.ofNullable(personDao.getPersonById(id));
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Transactional
    public PersonDomain findPersonInfoById(Person person){
        if(person != null){
            PersonDomain personDomain = new PersonDomain();
            personDomain.setFirstName(person.getFirstName());
            personDomain.setLastName(person.getLastName());
            personDomain.setAvatar(employeeDao.getEmployeeByPersonId(person).getAvatar());
            personDomain.setSSN(person.getSsn());
            personDomain.setDob(person.getDob());
            return personDomain;
        }
        return null;
    }

    @Transactional
    public Person findPersonByName(String firstName, String lastName){
        return personDao.getPersonByName(firstName, lastName);
    }

    @Transactional
    public Person updateInfo(PersonDomain personDomain, Integer personId){
        Person curPerson = personDao.getPersonById(personId);
        curPerson.setFirstName(personDomain.getFirstName());
        curPerson.setLastName(personDomain.getLastName());
        curPerson.setDob(personDomain.getDob());
        curPerson.setSsn(personDomain.getSSN());
        return personDao.saveInfo(curPerson);
    }

    @Transactional
    public Person updateContact(ContactDomain contactDomain, Integer personId){
        Person curPerson = personDao.getPersonById(personId);
        curPerson.setCellPhone(contactDomain.getCellPhone());
        curPerson.setAlternatePhone(contactDomain.getWorkPhone());
        curPerson.setEmail(contactDomain.getEmail());
        return personDao.saveInfo(curPerson);
    }
}
