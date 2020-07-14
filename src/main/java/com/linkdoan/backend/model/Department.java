package com.linkdoan.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.linkdoan.backend.dto.DepartmentDTO;
import lombok.Data;

import javax.persistence.*;
import com.linkdoan.backend.model.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="department")
public class Department {
    @Id
    @Column(name="department_id",unique = true,columnDefinition="CHAR(7)")
    private String departmentId;

    @Column(name="department_name")
    private String departmentName;

    @Column(name="department_type")
    private Integer departmentType;

    @Column(name="start_year")
    private Integer startYear; //date type

    @OneToOne
    @JoinColumn(name = "cheif_id")
    private Employee employee; //1 - 1 relationship

    @OneToMany(mappedBy="department")
    @Column(nullable = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Class> children;


}
