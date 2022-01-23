package com.example.hrinterface.entity;


import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PersonalDocument")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Path")
    private String path;

    @Column(name = "Title")
    private String title;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "EmployeeID")
    private int employeeID;
}