package com.example.hrinterface.domain;

import com.example.hrinterface.entity.Employee;
import com.example.hrinterface.entity.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegistrationProfile {
    public int id;
    public String name;
    public String workAuthorization;
    public String status;

    public RegistrationProfile(UserRole userRole, Employee employee){
        this.id = userRole.getID();
        this.name = employee.getPerson().getFirstName() + " " + employee.getPerson().getLastName();
        this.workAuthorization = employee.getVisaStatus().getVisaType();
        this.status = userRole.getStatus();
    }
}
