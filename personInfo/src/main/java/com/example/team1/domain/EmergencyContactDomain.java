package com.example.team1.domain;

import com.example.team1.entity.Address;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContactDomain {

    private String name;

    private String phone;

    private List<AddressDomain> addressDomains;
}
