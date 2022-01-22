package com.example.hrinterface.controller;

import com.example.hrinterface.domain.VisaDetail;
import com.example.hrinterface.domain.VisaProfile;
import com.example.hrinterface.entity.ApplicationWorkflow;
import com.example.hrinterface.entity.Employee;
import com.example.hrinterface.service.ProfileService;
import com.example.hrinterface.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class VisaController {
    @Autowired
    ProfileService profileService;
    @Autowired
    VisaService visaService;

    @GetMapping({"/api/hr/visa-profile", "/api/hr/homepage"})
    public List<VisaProfile> getVisaProfile(){
        List<Employee> employeeList = profileService.getAllEmployee();
        List<VisaProfile> visaProfileList = new ArrayList<>();
        for(int i =0; i < employeeList.size(); i++){
            visaProfileList.add(new VisaProfile(employeeList.get(i)));
        }
        return visaProfileList;
    }

    @GetMapping({"/api/hr/visa-info", "/api/hr/visa-info/{id}"})
    public Object getVisaDetail(@PathVariable(required = false)Integer id){
        if(id == null){
            return "error";
        }
        Employee e = profileService.findEmployeeByID(id);
        if(e == null){
            return "error";
        }
        else {
            ApplicationWorkflow a = visaService.getWorkflowByEmployeeId(id);
            VisaDetail v = new VisaDetail(e, a);
            return v;
        }
    }

    @PostMapping("/api/hr/{id}/visa-update")
    public Object updateInformation(@PathVariable Integer id, @RequestBody Map<String, Object> payload){
        profileService.updateVisaInfo(id, (String) payload.get("name"), (String) payload.get("startDate"), (String) payload.get("endDate"));
        visaService.updateVisaType(id, (String) payload.get("visaType"));
        return new VisaDetail(profileService.findEmployeeByID(id), visaService.getWorkflowByEmployeeId(id));
    }

    @PostMapping("/api/hr/{id}/review")
    public Object review(@PathVariable Integer id, @RequestBody Map<String, Object> payload){
        ApplicationWorkflow applicationWorkflow = visaService.getWorkflowByEmployeeId(id);
        applicationWorkflow.setCreatedDate(LocalDate.now().toString());
        if(payload.get("review").equals("reject")){
            applicationWorkflow.setStatus("reject");
            applicationWorkflow.setComments("Your request has been rejected. Please upload a new file of " + applicationWorkflow.getType());
            visaService.updateApplicationWorkflow(applicationWorkflow);
            return applicationWorkflow;
        }
        else if (payload.get("review").equals("approve")){
            applicationWorkflow.setStatus("waiting");
            if(applicationWorkflow.getType().equals("EADReceipt")){
                applicationWorkflow.setType("EADCard");
                applicationWorkflow.setComments("Your EADReicept has been accepted. Please upload your EADCard");
                visaService.updateApplicationWorkflow(applicationWorkflow);
                return applicationWorkflow;
            }
            else if(applicationWorkflow.getType().equals("EADCard")){
                applicationWorkflow.setType("I983");
                applicationWorkflow.setComments("You are all set!");
                visaService.updateApplicationWorkflow(applicationWorkflow);
                return applicationWorkflow;
            }
            else if(applicationWorkflow.getType().equals("I983")){
                applicationWorkflow.setType("I983");
                applicationWorkflow.setComments("You are all set!");
                visaService.updateApplicationWorkflow(applicationWorkflow);
                return applicationWorkflow;
            }
        }
        return applicationWorkflow;
    }
}
