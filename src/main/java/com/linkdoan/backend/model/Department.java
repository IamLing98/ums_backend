package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.DepartmentDTO;
import lombok.Data;

import javax.persistence.*;
import com.linkdoan.backend.model.Employee;

import java.util.Set;

@Data
@Entity
@Table(name="department")
public class Department {
    @Id
    @Column(name="department_id",unique = true,length = 10)
    private String departmentId;

    @Column(name="department_name")
    private String departmentName;

    @Column(name="department_type")
    private Integer departmentType;

    @OneToOne
    @JoinColumn(name = "cheif_id")
    private Employee employee; //1 - 1 relationship
    @OneToMany(mappedBy="department")
    private Set<Class> classes;

}
