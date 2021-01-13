package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class EmployeeDTO  {

    private String employeeId;

    private String fullName;

    private LocalDate dateBirth;

    private Integer sex;

    private String placeBorn;

    private String contactAddress;

    private String phoneNumber;

    private String email;

    private Integer degree;

    private Integer degreeDetails;

    private Integer scientificTitles;

    private Integer scientificTitlesGetYear;

    private Integer employeeType;

    private LocalDate startWork;

    private String avatar;

    private String departmentId;

    public Employee toModel(){
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFullName(fullName);
        employee.setDateBirth(dateBirth);
        employee.setSex(sex);
        employee.setPlaceBorn(placeBorn);
        employee.setContactAddress(contactAddress);;
        employee.setPhoneNumber(phoneNumber);
        employee.setEmail(email);
        employee.setDegree(degree);
        employee.setDegreeDetails(degreeDetails);
        employee.setScientificTitles(scientificTitles);
        employee.setScientificTitlesGetYear(scientificTitlesGetYear);;
        employee.setEmployeeType(employeeType);;
        employee.setStartWork(startWork);
        employee.setAvatar(avatar);
        employee.setDepartmentId(departmentId);
        return employee;
    }


    public EmployeeDTO(String employeeId, String fullName, LocalDate dateBirth, Integer sex, String placeBorn,
                       String contactAddress, String phoneNumber, String email, Integer degree, Integer degreeDetails,
                       Integer scientificTitles, Integer scientificTitlesGetYear, Integer employeeType, LocalDate startWork,
                       String avatar, String departmentId) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.dateBirth = dateBirth;
        this.sex = sex;
        this.placeBorn = placeBorn;
        this.contactAddress = contactAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.degree = degree;
        this.degreeDetails = degreeDetails;
        this.scientificTitles = scientificTitles;
        this.scientificTitlesGetYear = scientificTitlesGetYear;
        this.employeeType = employeeType;
        this.startWork = startWork;
        this.avatar = avatar;
        this.departmentId = departmentId;
    }
}
