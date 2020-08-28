package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.StudentDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @Size(min=9, max=9)
    @Column(name = "student_id",unique = true,columnDefinition="CHAR(9)")
    private String studentId;

    @Column(name = "first_name", columnDefinition = "VARCHAR(45)")
    private String firstName;

    @Column(name = "last_name" , columnDefinition = "VARCHAR(45)")
    private String lastName;

    @Column(name = "full_name", columnDefinition = "TEXT")
    private String fullName;

    @Column(name = "date_birth")
    private java.sql.Date dateBirth;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "nation",columnDefinition = "varchar(30)")
    private String nation;

    @Column(name = "ethnic", columnDefinition = "varchar(30)")
    private String ethnic;

    @Column(name = "home_address", columnDefinition = "text")
    private String homeAddress;

    @Column(name = "current_address", columnDefinition = "text")
    private String currentAddress;

    @Column(name = "email", columnDefinition = "char(40)")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "father_date_birth")
    private Integer fatherDateBirth;

    @Column(name = "father_work")
    private String fatherWork;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "mother_date_birth")
    private Integer motherDateBirth;

    @Column(name = "mother_work")
    private String motherWork;

    @Column(name = "family_phone_number", columnDefinition = "char(20)")
    private String familyPhoneNumber;

    @Column(name = "start_school")
    private Date startSchool;

    @Column(name = "religion", columnDefinition = "varchar(45)")
    private String religion;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "class_id",columnDefinition="CHAR(6)")
    private String classId;

    @Column(name = "department_id",columnDefinition="CHAR(10)")
    private String departmentId;

    @Column(name = "status", columnDefinition="INT" )
    private Integer status;

    public StudentDTO toDTO(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAvatar(this.avatar);
        studentDTO.setClassId(this.classId);
        studentDTO.setCurrentAddress(this.currentAddress);
        studentDTO.setDateBirth(this.dateBirth.toString());
        studentDTO.setDepartmentId(this.departmentId);
        studentDTO.setEmail(this.email);
        studentDTO.setFatherDateBirth(this.fatherDateBirth.toString());
        studentDTO.setFatherWork(this.fatherWork);
        studentDTO.setFirstName(this.firstName);
        studentDTO.setHomeAddress(this.fatherName);
        studentDTO.setFamilyPhoneNumber(this.familyPhoneNumber);
        studentDTO.setLastName(this.lastName);
        studentDTO.setMotherDateBirth(this.motherDateBirth.toString());
        studentDTO.setMotherName(this.motherName);
        studentDTO.setMotherWork(this.motherWork);
        studentDTO.setNationality(this.nation);
        studentDTO.setPhoneNumber(this.phoneNumber);
        studentDTO.setReligion(this.religion);
        studentDTO.setSex(this.sex.toString());
        studentDTO.setStartSchool(this.startSchool.toString());
        studentDTO.setStudentId(this.studentId);
        studentDTO.setFullName(this.fullName);
        studentDTO.setStatus(this.status.toString());
        return studentDTO;
    }
}
