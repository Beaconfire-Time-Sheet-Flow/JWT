package com.example.hrinterface.domain;

import com.example.hrinterface.entity.Employee;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VisaProfile {
    public int ID;
    public String name;
    public String workAuthorization;
    public String ExpirationDate;
    public int dayLeft;

    public VisaProfile(Employee employee){
        this.ID = employee.getID();
        this.name = employee.getPerson().getFirstName() + " " + employee.getPerson().getLastName();
        this.workAuthorization = employee.getVisaStatus().getVisaType();
        this.ExpirationDate = employee.getVisaEndDate();
//        Date date1 = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.parse(employee.getVisaEndDate());
        this.dayLeft = (int) ChronoUnit.DAYS.between(date1, date2);
    }
}
