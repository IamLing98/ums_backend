package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class EmployeeDTO {


    @Id
    @Column(name = "employee_id", columnDefinition = "char(10)")
    private String employeeId;

    private String departmentId;

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

    private String ethnic;

    private String bornPlace;

    private String homeTown;

    private String permanentResidence;

    private String nationality;

    private String religion;

    private LocalDate CPStartDate;

    private String identityNumber;

    private LocalDate identityCreatedDate;

    private String identityCreatedPlace;

    private String bankNumber;

    private Department department;

    private List<TeacherEducationTimeLine> teacherEducationTimeLineList;

    private List<TeacherWorkTimeLine> teacherWorkTimeLineList;

    private List<Subject>  subjectList;

    public EmployeeDTO(String employeeId, String departmentId, Integer employeeDepartmentLevel, String officeId,
                       Integer employeeOfficeLevel, String fullName, LocalDate dateBirth, Integer sex, String placeBorn,
                       String contactAddress, String phoneNumber, String email, Integer degree, Integer degreeDetails,
                       Integer scientificTitles, Integer scientificTitlesGetYear, LocalDate startWork, String avatar,
                       String ethnic, String bornPlace, String homeTown, String permanentResidence, String nationality,
                       String religion, LocalDate CPStartDate, String identityNumber, LocalDate identityCreatedDate,
                       String identityCreatedPlace, String bankNumber, Department department)
                        {
        this.employeeId = employeeId;
        this.departmentId = departmentId;
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
        this.ethnic = ethnic;
        this.bornPlace = bornPlace;
        this.homeTown = homeTown;
        this.permanentResidence = permanentResidence;
        this.nationality = nationality;
        this.religion = religion;
        this.CPStartDate = CPStartDate;
        this.identityNumber = identityNumber;
        this.identityCreatedDate = identityCreatedDate;
        this.identityCreatedPlace = identityCreatedPlace;
        this.bankNumber = bankNumber;
        this.department = department;
        this.teacherEducationTimeLineList = teacherEducationTimeLineList;
        this.teacherWorkTimeLineList = teacherWorkTimeLineList;
        this.subjectList = subjectList;
    }

    public Employee toModel() {
        Employee employeeDTO = new Employee();
        employeeDTO.setEmployeeId(employeeId);
        employeeDTO.setDepartmentId(departmentId);
        employeeDTO.setEmployeeDepartmentLevel(employeeDepartmentLevel);
        employeeDTO.setOfficeId(officeId);
        employeeDTO.setEmployeeOfficeLevel(employeeOfficeLevel);
        employeeDTO.setFullName(fullName);
        employeeDTO.setDateBirth(dateBirth);
        employeeDTO.setSex(sex);
        employeeDTO.setPlaceBorn(placeBorn);
        employeeDTO.setContactAddress(contactAddress);
        employeeDTO.setPhoneNumber(phoneNumber);
        employeeDTO.setEmail(email);
        employeeDTO.setDegree(degree);
        employeeDTO.setDegreeDetails(degreeDetails);
        employeeDTO.setScientificTitles(scientificTitles);
        employeeDTO.setScientificTitlesGetYear(scientificTitlesGetYear);
        employeeDTO.setStartWork(startWork);
        employeeDTO.setAvatar(avatar);
        employeeDTO.setEthnic(ethnic);
        employeeDTO.setBornPlace(bornPlace);
        employeeDTO.setHomeTown(homeTown);
        employeeDTO.setPermanentResidence(permanentResidence);
        employeeDTO.setNationality(nationality);
        employeeDTO.setReligion(religion);
        employeeDTO.setCPStartDate(CPStartDate);
        employeeDTO.setIdentityNumber(identityNumber);
        employeeDTO.setIdentityCreatedDate(identityCreatedDate);
        employeeDTO.setIdentityCreatedPlace(identityCreatedPlace);
        employeeDTO.setBankNumber(bankNumber);
        return employeeDTO;
    }
}
