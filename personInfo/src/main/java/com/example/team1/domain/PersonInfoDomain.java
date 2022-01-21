package com.example.team1.domain;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfoDomain {

    private PersonDomain personDomain;

    private EmployeeDomain employeeDomain;

    private List<EmergencyContactDomain> emergencyContactDomains;

    private List<AddressDomain> addressDomains;

    private List<PersonalDocsDomain> personalDocsDomains;

    private VisaStatusDomain visaStatusDomain;

}
