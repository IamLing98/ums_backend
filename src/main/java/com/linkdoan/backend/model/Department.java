package com.linkdoan.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.linkdoan.backend.dto.ClassDTO;
import com.linkdoan.backend.dto.DepartmentDTO;
import lombok.Data;

import javax.persistence.*;

import com.linkdoan.backend.model.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "department")
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "department_id", unique = true, columnDefinition = "CHAR(7)")
    private String departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_type")
    private Integer departmentType;

    @Column(name = "start_year")
    private Integer startYear; //date type

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(nullable = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Class> children;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(nullable = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Employee> employeeList;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(nullable = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Specialized> specializedList;

    public DepartmentDTO toDTO() {
        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setDepartmentId(this.departmentId);
        departmentDTO.setDepartmentName(this.departmentName);
        departmentDTO.setDepartmentType(this.departmentType.toString());
        departmentDTO.setStartYear(this.startYear.toString());
        departmentDTO.setChildren(this.children);
        departmentDTO.setEmployeeList(this.employeeList);
        departmentDTO.setSpecializedList(this.specializedList);
        return departmentDTO;
    }

}
