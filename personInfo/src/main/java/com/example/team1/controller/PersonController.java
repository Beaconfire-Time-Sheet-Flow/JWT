package com.example.team1.controller;

import com.example.team1.domain.*;
import com.example.team1.entity.Address;
import com.example.team1.entity.Contact;
import com.example.team1.entity.Employee;
import com.example.team1.entity.Person;
import com.example.team1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/personInfo")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private PersonalDocService personalDocService;

    @Autowired
    private VisaService visaService;

    @GetMapping("/")
    public ResponseEntity<PersonInfoDomain> getPersonalInfo(@RequestParam("id") Integer personId){
        Person person = personService.findPersonById(personId);
        PersonInfoDomain res = new PersonInfoDomain();
        if(person!=null){
            Employee employee = employeeService.getEmployeeByPersonId(person);
            if(employee!= null){
                res.setFirstName(person.getFirstName());
                res.setLastName(person.getLastName());
                res.setMiddleName(person.getMiddleName());
                res.setAvatar(employee.getAvatar());
                res.setGender(person.getGender());
                res.setDriverLicense(employee.getDriverLicense());
                res.setDriverLicense(employee.getDriverLicenseExpirationDate());
                res.setEmail(person.getEmail());
                res.setCellPhone(person.getCellPhone());
                res.setAlternatePhone(person.getAlternatePhone());
                EmployeeDomain employeeDomain = employeeService.getEmployeeInfoByPersonId(person);
                List<AddressDomain> addressList = addressService.getAddressByPersonId(person);
                List<EmergencyContactDomain> emergencyContactDomains = contactService.getEmergencyByPersonId(person);
                List<PersonalDocsDomain> docsDomains = personalDocService.getPersonalDocInfoByEmployeeId(employee);
                res.setAddressList(addressList);
                res.setDocumentList(docsDomains);
                res.setEmployment(employeeDomain);
                res.setContact(emergencyContactDomains);
                res.setId(personId);
                res.setSSN(person.getSsn());
                res.setDob(person.getDOB());
                return ResponseEntity.ok(res);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/updateAddress")
    public ResponseEntity<List<AddressDomain>> updateAddress(@RequestBody List<AddressDomain> addressDomains,
                                                       @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        Person person = personService.findPersonById(personId);
        addressService.updateAddress(addressDomains, person);
        return ResponseEntity.ok(addressDomains);
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<EmployeeDomain> updateEmployee(@RequestBody EmployeeDomain employeeDomain, @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        Person person = personService.findPersonById(personId);
        employeeService.updateEmployee(employeeDomain, person);
        return ResponseEntity.ok(employeeDomain);
    }

    @PostMapping("/updateDocs")
    public ResponseEntity<List<PersonalDocsDomain>> updateDocs(
            @RequestBody List<PersonalDocsDomain> personalDocsDomainList,
            @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        Person person = personService.findPersonById(personId);
        Employee employee = employeeService.getEmployeeByPersonId(person);
        if(person==null || employee==null){
            return ResponseEntity.notFound().build();
        }
        personalDocService.updatePersonalDocs(personalDocsDomainList, employee);
        return ResponseEntity.ok(personalDocsDomainList);
    }

    @PostMapping("/updateContact")
    public ResponseEntity<ContactDomain> updateContact(@RequestBody ContactDomain contactDomain, @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        personService.updateContact(contactDomain, personId);
        return ResponseEntity.ok(contactDomain);
    }

    @PostMapping("/updateEmergency")
    public ResponseEntity<EmergencyContactDomain> updateEmergency(
            @RequestBody EmergencyContactDomain contactDomain,
            @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        Person person = personService.findPersonById(personId);
        if(person==null){
            return ResponseEntity.notFound().build();
        }
        contactService.updateEmergency(contactDomain, person);
        return ResponseEntity.ok(contactDomain);
    }

    @PostMapping("/updatePersonInfo")
    public ResponseEntity<UpdatePersonInfo> updatePerson(@RequestBody UpdatePersonInfo updatePersonInfo, @RequestParam(name = "id", required = false) Integer personId){
        if(personId==null && updatePersonInfo.getID()!=null){
            personService.updateInfo(updatePersonInfo);
            return ResponseEntity.ok(updatePersonInfo);
        }else if(personId<=0 || updatePersonInfo.getID()==null || updatePersonInfo.getID()<=0){
            return ResponseEntity.notFound().build();
        }
        personService.updateInfoWithGivenId(updatePersonInfo, personId);
        return ResponseEntity.ok(updatePersonInfo);
    }

    @PostMapping("/addPersonalDocs")
    public ResponseEntity<List<PersonalDocsDomain>> addNewDocs(
            @RequestBody List<PersonalDocsDomain> docsDomainList,
            @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        Person person = personService.findPersonById(personId);
        Employee employee = employeeService.getEmployeeByPersonId(person);
        if(person==null || employee==null){
            return ResponseEntity.notFound().build();
        }
        personalDocService.addNewDocs(docsDomainList, employee);
        return ResponseEntity.ok(docsDomainList);
    }
}
