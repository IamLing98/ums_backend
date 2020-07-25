package com.linkdoan.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linkdoan.backend.dto.EmployeeDTO;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id", columnDefinition = "char(10)")
    private String employeeId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "date_birth")
    private Date dateBirth;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "current_address")
    private String currentAddress;

    @Column(name = "degree")
    private String degree;

    @Column(name = "phone")
    private String phone;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "email")
    private String email;

    @Column(name = "start_work")
    private Date startWork;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", nullable = true, insertable = false, updatable = false)
    //@JsonBackReference// quat the fackkkkk, cai nay lam treo sys dcmm
    @JsonIgnore
    private Department department;

    public String getDepartmentId() {
        return department.getDepartmentId();
    }

    public String getDepartmentName() {
        return department.getDepartmentName();
    }

    public EmployeeDTO toDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(this.employeeId);
        employeeDTO.setFullName(this.fullName);
        employeeDTO.setSex(this.sex);
        employeeDTO.setDateBirth(this.dateBirth);
        employeeDTO.setHomeAddress(this.homeAddress);
        employeeDTO.setCurrentAddress(this.currentAddress);
        employeeDTO.setDegree(this.degree);
        employeeDTO.setPhone(this.phone);
        employeeDTO.setHomePhone(this.homePhone);
        employeeDTO.setEmail(this.email);
        employeeDTO.setStartWork(this.startWork);
        employeeDTO.setAvatar(this.avatar);
        employeeDTO.setDepartmentId(this.getDepartmentId());
        return employeeDTO;
    }
}
