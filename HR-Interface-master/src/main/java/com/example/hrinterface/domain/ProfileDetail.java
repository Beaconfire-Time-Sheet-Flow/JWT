package com.example.hrinterface.domain;

import com.example.hrinterface.entity.Address;
import com.example.hrinterface.entity.Contact;
import com.example.hrinterface.entity.Employee;
import com.example.hrinterface.entity.PersonalDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProfileDetail {
    @JsonProperty("ID")
    int ID;
    String firstName;
    String lastName;
    String middleName;
    String avatar;
    @JsonProperty("DOB")
    String DOB;
    String gender;
    @JsonProperty("SSN")
    String SSN;
    String driverLicense;
    String driverLicense_ExpirationDate;
    String email;
    String cellphone;
    String alternatePhone;
    Employment employment;
    Contact contact;
    List<Address> addressList;
    List<PersonalDocument> documentList;

    public ProfileDetail(Employee employee, Contact contact, List<Address> addressList){
        this.ID = employee.getID();
        this.firstName = employee.getPerson().getFirstName();
        this.lastName = employee.getPerson().getLastName();
        this.middleName = employee.getPerson().getMiddleName();
        this.avatar = employee.getAvatar();
        this.DOB = employee.getPerson().getDOB();
        this.gender = employee.getPerson().getGender();
        this.SSN = employee.getPerson().getSSN();
        this.driverLicense = employee.getDriverLicense();
        this.driverLicense_ExpirationDate = employee.getDriverLicense_ExpirationDate();
        this.email = employee.getPerson().getEmail();
        this.cellphone = employee.getPerson().getCellphone();
        this.alternatePhone = employee.getPerson().getAlternatePhone();
        this.employment = new Employment(employee);
        this.contact = contact;
        this.addressList = addressList;
        this.documentList = new ArrayList<>();
    }
}
