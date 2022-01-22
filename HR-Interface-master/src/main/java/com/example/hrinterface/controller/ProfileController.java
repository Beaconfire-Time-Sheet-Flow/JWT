package com.example.hrinterface.controller;

import com.example.hrinterface.domain.Profile;
import com.example.hrinterface.domain.ProfileDetail;
import com.example.hrinterface.entity.Employee;
import com.example.hrinterface.entity.Person;
import com.example.hrinterface.entity.VisaStatus;
import com.example.hrinterface.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping({"/api/hr/employee-profile"})
    public List<Profile> getEmployeeProfile(){
        List<Employee> employeeList = profileService.getAllEmployee();
        List<Profile> profileList = new ArrayList<>();
        for(int i = 0; i<employeeList.size(); i++){
            Employee e = employeeList.get(i);
            profileList.add(new Profile(e.getID(),e.getPerson().getFirstName() + " " + e.getPerson().getLastName(), e.getPerson().getSSN(), e.getStartDate(), e.getVisaStatus().getVisaType()));
        }
        return profileList;
//        Employee employee = new Employee(1, 1, null, 1, "1", "2", "3", null, 1, "4", "5", null, null, new Person(), new VisaStatus());

    }

    @GetMapping({"/api/hr/employee-info", "/api/hr/employee-info/{id}"})
    public Object getEmployeeDetail(@PathVariable(required = false)Integer id){
        if(id == null){
            return "error";
        }
        Employee e = profileService.findEmployeeByID(id);
        if(e == null){
            return "error";
        }
        else {
            ProfileDetail pd = new ProfileDetail(e, profileService.getContactById(e.getID()), profileService.getAddressByPersonId(e.getID()));
            return pd;
        }
    }
}
