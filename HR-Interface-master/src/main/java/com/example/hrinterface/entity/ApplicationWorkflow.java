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
@Table(name = "applicationworkflow")
public class ApplicationWorkflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty("ID")
    public int ID;

    @Column(name = "EmployeeID")
    public int employeeID;

    @Column(name = "createdDate")
    public String createdDate;

    @Column(name = "status")
    public String status;

    @Column(name = "comments")
    public String comments;

    @Column(name = "type")
    public String type;
}
