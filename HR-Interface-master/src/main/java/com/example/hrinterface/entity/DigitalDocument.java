package com.example.hrinterface.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="DigitalDocument")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DigitalDocument implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Type")
    private String type;

    @Column(name = "Required")
    private Boolean required;

    @Column(name = "TemplateLocation")
    private String templateLocation;

    @Column(name = "Description")
    private String description;

}
