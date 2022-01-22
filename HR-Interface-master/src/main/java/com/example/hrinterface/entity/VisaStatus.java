package com.example.hrinterface.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "visaStatus")
public class VisaStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty("ID")
    public int ID;

    @Column(name = "visaType")
    public String visaType;

    @Column(name = "active")
    public boolean active;

    @Column(name = "CreateUser")
    public int createUser;

    @OneToOne
    @JoinColumn(name = "createUser")
    public Person person;
}
