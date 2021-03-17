package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "employee_coefficient_level")
@Entity
public class EmployeeCoefficientLevel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_level_id")
    private Long employeeLevelId;

    @Column(name = "employee_coefficient_level_name")
    private String employeeCoefficientLevelName;

    @Column(name = "value")
    private Double value;

}
