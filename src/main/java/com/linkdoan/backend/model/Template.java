package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "template")
@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "pathName")
    private String pathName;
}
