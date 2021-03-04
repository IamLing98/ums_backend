package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.util.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import org.hibernate.annotations.Parameter;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @GenericGenerator(
            name = "employee_seq",
            strategy = "com.linkdoan.backend.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EMPLOYEE"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")})
    @Column(name = "employee_id", columnDefinition = "char(11)")
    private String employeeId;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "em_de_level")
    private Integer employeeDepartmentLevel;

    @Column(name = "office_id")
    private String officeId;

    @Column(name = "em_off_level")
    private Integer employeeOfficeLevel;

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

    @Column(name = "start_work")
    private LocalDate startWork;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "ethnic")
    private String ethnic;


    //nơi sinh
    @Column(name = "born_place", columnDefinition = "VARCHAR(45)")
    private String bornPlace;

    //quê quán
    @Column(name = "home_town", columnDefinition = "VARCHAR(200)")
    private String homeTown;

    //hộ khẩu thường trú
    @Column(name = "permanent_residence", columnDefinition = "varchar(200)")
    private String permanentResidence;

    //quốc tịch
    @Column(name = "nationality")
    private String nationality;

    //ton giao
    @Column(name = "religion", columnDefinition = "varchar(45)")
    private String religion;

    //ngày vào đảng
    @Column(name = "CP_startDate", columnDefinition = "DATE")
    private LocalDate CPStartDate;

    //thẻ căn cước/CMND
    @Column(name = "identity_number", columnDefinition = "Char(20)")
    private String identityNumber;

    //thẻ căn cước/CMND ngày cấp
    @Column(name = "identity_created_date", columnDefinition = "DATE")
    private LocalDate identityCreatedDate;

    //thẻ căn cước/CMND nơi cấp
    @Column(name = "identity_created_place", columnDefinition = "VARCHAR(200)")
    private String identityCreatedPlace;

    //số tài khoản ngân hàng
    @Column(name = "bank_number", columnDefinition = "VARCHAR(45)")
    private String bankNumber;


    public EmployeeDTO toDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
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
