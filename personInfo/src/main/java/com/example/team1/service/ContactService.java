package com.example.team1.service;

import com.example.team1.DAO.Dao.ContactDao;
import com.example.team1.domain.AddressDomain;
import com.example.team1.domain.EmergencyContactDomain;
import com.example.team1.entity.Address;
import com.example.team1.entity.Contact;
import com.example.team1.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private AddressService addressService;

    @Transactional
    public List<EmergencyContactDomain> getEmergencyByPersonId(Person person){
        List<EmergencyContactDomain> domainList = new ArrayList<>();
        List<Contact> contacts = contactDao.getContactByPersonId(person);
        if(contacts!=null&& contacts.size()>0){
            for(Contact contact : contacts){
                if(contact.getIsEmergency()){
                    EmergencyContactDomain emergencyContactDomain = new EmergencyContactDomain(
                            person.getFirstName()+person.getLastName(),
                            person.getCellPhone(),
                            addressService.getAddressByPersonId(person));
                    domainList.add(emergencyContactDomain);
                }
            }
        }
        return domainList;
    }
}
