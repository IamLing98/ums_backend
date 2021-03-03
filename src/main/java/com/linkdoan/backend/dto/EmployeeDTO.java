package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
public class EmployeeDTO {

    private String employeeId;

    private Department department;

    private Integer employeeDepartmentLevel;

    private String officeId;

    private Integer employeeOfficeLevel;

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

    private LocalDate startWork;

    private String avatar;

    private List<TeacherEducationTimeLine> teacherEducationTimeLineList;

    private List<TeacherWorkTimeLine> teacherWorkTimeLineList;

    private List<Subject>  subjectList;

    public EmployeeDTO(String employeeId, Department department, Integer employeeDepartmentLevel, String officeId, Integer employeeOfficeLevel,
                       String fullName, LocalDate dateBirth, Integer sex, String placeBorn, String contactAddress, String phoneNumber,
                       String email, Integer degree, Integer degreeDetails, Integer scientificTitles, Integer scientificTitlesGetYear,
                       LocalDate startWork, String avatar  ) {
        this.employeeId = employeeId;
        this.department = department;
        this.employeeDepartmentLevel = employeeDepartmentLevel;
        this.officeId = officeId;
        this.employeeOfficeLevel = employeeOfficeLevel;
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
        this.startWork = startWork;
        this.avatar = avatar;
    }

    public Employee toModel() {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFullName(fullName);
        employee.setDateBirth(dateBirth);
        employee.setSex(sex);
        employee.setPlaceBorn(placeBorn);
        employee.setContactAddress(contactAddress);
        employee.setPhoneNumber(phoneNumber);
        employee.setEmail(email);
        employee.setDegree(degree);
        employee.setDegreeDetails(degreeDetails);
        employee.setScientificTitles(scientificTitles);
        employee.setScientificTitlesGetYear(scientificTitlesGetYear);
        employee.setStartWork(startWork);
        employee.setAvatar(avatar);
        employee.setDepartmentId(department.getDepartmentId());
        return employee;
    }
}
