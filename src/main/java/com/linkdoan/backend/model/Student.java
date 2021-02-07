package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.StudentDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @Size(min = 9, max = 9)
    @Column(name = "student_id", unique = true, columnDefinition = "CHAR(9)")
    private String studentId;

    @Column(name = "full_name", columnDefinition = "TEXT")
    private String fullName;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @Column(name = "nick_name", columnDefinition = "VARCHAR(45)")
    private String nickName;

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

    //dân tộc
    @Column(name = "ethnic")
    private String ethnic;

    //ton giao
    @Column(name = "religion", columnDefinition = "varchar(45)")
    private String religion;

    //khu vuc tuyen sinh
    @Column(name = "enrollment_area", columnDefinition = "INT")
    private Integer enrollmentArea;

    // đôi tượng ưu tiên
    @Column(name = "priority_type", columnDefinition = "INT")
    private Integer priorityType;

    //chính sách ưu tiên
    @Column(name = "incentives_type", columnDefinition = "INT")
    private Integer incentivesType;

    //trình độ văn hoá
    @Column(name = "education_level", columnDefinition = "VARCHAR(45)")
    private String educationLevel;

    //thành phần gia đình
    @Column(name = "family_element", columnDefinition = "VARCHAR(45)")
    private String familyElement;

    //ngày vào đoàn
    @Column(name = "CYU_startDate", columnDefinition = "DATE")
    private LocalDate CYUStartDate;

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

    //địa chỉ liên lạc
    @Column(name = "contact_address", columnDefinition = "varchar(200)")
    private String contactAddress;

    @Column(name = "note")
    private String note;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "year_class_id", columnDefinition = "CHAR(6)")
    private String yearClassId;

    @Column(name = "status", columnDefinition = "INT")
    private Integer status;

    //số báo danh
    @Column(name = "enroll_id", columnDefinition = "Char(15)")
    private String enrollId;

    //hình thức xét tuyển Admission
    @Column(name = "admission_type", columnDefinition = "INT")
    private Integer admissionType;

    //CTDT theo đuổi
    @Column(name = "education_program_id", columnDefinition = "CHAR(11)")
    private String educationProgramId;

    @Column(name = "created_date", columnDefinition = "DATE")
    private LocalDate createdDate;

    public StudentDTO toDTO() {
        StudentDTO student = new StudentDTO();
        student.setStudentId(this.studentId);
        student.setFullName(this.fullName);
        student.setSex(this.sex);
        student.setDateBirth(this.dateBirth);
        student.setNickName(this.nickName);
        student.setHomeTown(this.homeTown);
        student.setPermanentResidence(this.permanentResidence);
        student.setEthnic(this.ethnic);
        student.setReligion(this.religion);
        student.setEnrollmentArea(this.enrollmentArea);
        student.setPriorityType(this.priorityType);
        student.setIncentivesType(this.incentivesType);
        student.setFamilyElement(this.familyElement);
        student.setCYUStartDate(this.getCYUStartDate());
        student.setCPStartDate(this.CPStartDate);
        student.setIdentityNumber(this.identityNumber);
        student.setIdentityCreatedDate(this.identityCreatedDate);
        student.setIdentityCreatedPlace(this.identityCreatedPlace);
        student.setBankNumber(this.bankNumber);
        student.setEmail(this.email);
        student.setPhoneNumber(this.phoneNumber);
        student.setFatherName(this.fatherName);
        student.setFatherDateBirth(this.fatherDateBirth);
        student.setFatherWork(this.fatherWork);
        student.setMotherName(this.motherName);
        student.setMotherDateBirth(this.motherDateBirth);
        student.setMotherWork(this.motherWork);
        student.setYearClassId(this.yearClassId);
        student.setCreatedDate(this.createdDate);
        student.setContactAddress(this.contactAddress);
        return student;
    }
}
