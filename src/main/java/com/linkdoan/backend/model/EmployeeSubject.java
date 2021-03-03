package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Table(name="employee_subject")
@Entity
@Data
public class EmployeeSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="employee_id")
    private String employeeId;

    @Column(name="subject_id")
    private String subjectId;
}
