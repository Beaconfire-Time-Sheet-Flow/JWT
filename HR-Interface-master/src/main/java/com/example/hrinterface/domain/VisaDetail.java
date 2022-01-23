package com.example.hrinterface.domain;

import com.example.hrinterface.entity.ApplicationWorkflow;
import com.example.hrinterface.entity.Employee;
import com.example.hrinterface.entity.PersonalDocument;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisaDetail {
    public String name;
    public String visaType;
    public String startDate;
    public String endDate;
    public List<PersonalDocument> documentList;
    public String type;

    public VisaDetail(Employee employee, ApplicationWorkflow applicationWorkflow){
        this.name = employee.getPerson().getFirstName() + " " + employee.getPerson().getLastName();
        this.visaType = employee.getVisaStatus().getVisaType();
        this.startDate = employee.getVisaStartDate();
        this.endDate = employee.getVisaEndDate();
        this.documentList = new ArrayList<>();
        this.type = applicationWorkflow.getType();  //for next step
    }
}
