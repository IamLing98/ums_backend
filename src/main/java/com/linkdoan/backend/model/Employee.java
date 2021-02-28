package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id", columnDefinition = "char(10)")
    private String employeeId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "place_born")
    private String placeBorn;

    @Column(name = "contact_address")
    private String contactAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "degree")
    private Integer degree;

    @Column(name = "degree_details")
    private Integer degreeDetails;

    @Column(name = "scientific_titles", columnDefinition = "INT")
    private Integer scientificTitles;

    @Column(name = "scientific_titles_get_year")
    private Integer scientificTitlesGetYear;

    @Column(name = "employee_type_id")
    private Long employeeTypeId;

    @Column(name = "start_work")
    private LocalDate startWork;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "office_id", columnDefinition = "CHAR(7)")
    private String officeId;

    @Column(name = "staff_level", columnDefinition = "INT")
    private Integer staffLevel;

//    public EmployeeDTO toDTO() {
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        employeeDTO.setEmployeeId(this.employeeId);
//        employeeDTO.setFullName(this.fullName);
//        employeeDTO.setSex(this.sex);
//        employeeDTO.setDateBirth(this.dateBirth);
//        employeeDTO.setHomeAddress(this.homeAddress);
//        employeeDTO.setCurrentAddress(this.currentAddress);
//        employeeDTO.setDegree(this.degree);
//        employeeDTO.setPhone(this.phone);
//        employeeDTO.setHomePhone(this.homePhone);
//        employeeDTO.setEmail(this.email);
//        employeeDTO.setStartWork(this.startWork);
//        employeeDTO.setAvatar(this.avatar);
//        employeeDTO.setDepartmentId(this.getDepartmentId());
//        return employeeDTO;
//    }
}
