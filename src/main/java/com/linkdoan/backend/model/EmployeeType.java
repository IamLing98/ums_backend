package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="employee_type")
public class EmployeeType {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="employee_type_name")
    private String employeeTypeName;

    @Column(name="description")
    private String description;
}
