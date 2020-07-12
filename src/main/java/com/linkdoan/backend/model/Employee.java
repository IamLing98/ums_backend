package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.EmployeeDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Data
@Table(name="employee")
public class Employee  {
    @Id
    @Column(name="employee_id")
    private String employeeId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="sex")
    private Integer sex;

    @Column(name="date_birth")
    private Date dateBirth;

    @Column(name="home_address")
    private String homeAddress;

    @Column(name="current_address")
    private String currentAddress;

    @Column(name="degree")
    private String degree;

    @Column(name="phone")
    private String phone;

    @Column(name="home_phone")
    private String homePhone;

    @Column(name="email")
    private String email;

    @Column(name="start_work")
    private Date startWork;

    @Column(name="avatar")
    private String avatar;
    public EmployeeDTO toDTO(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(this.employeeId);
        employeeDTO.setFirstName(this.firstName);
        employeeDTO.setLastName(this.lastName);
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
        return employeeDTO;
    }
}
