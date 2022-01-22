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
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty("ID")
    public int ID;

    @Column(name = "personID")
    public int personID;

    @Column(name = "name")
    public String name;

    @Column(name = "phone")
    public String phone;

    @Column(name = "address")
    public String address;

    @Column(name = "relationship")
    public String relationship;

    @Column(name = "title")
    public String title;

    @JsonProperty("isReference")
    @Column(name = "isReference")
    public boolean isReference;

    @JsonProperty("isEmergency")
    @Column(name = "isEmergency")
    public boolean isEmergency;
}
