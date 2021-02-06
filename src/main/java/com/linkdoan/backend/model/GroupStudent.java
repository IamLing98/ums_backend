package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Table(name="group_student")
@Entity
@Data
public class GroupStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="group_id")
    private Long groupId;

    @Column(name="education_program_id")
    private String educationProgramId;

    @Column(name="term")
    private Integer term;
}
