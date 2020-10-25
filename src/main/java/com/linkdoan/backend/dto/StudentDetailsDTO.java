package com.linkdoan.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailsDTO {

    private String studentId;

    private String fullName;

    private Integer sex;

    private LocalDate dateBirth;

    private String nickName;

    private String homeTown;

    //quốc tịch
    private Integer nationalityId;

    //quốc tịch
    private String nationalityName;

    // nơi sinh
    private String bornPlace;

    //hộ khẩu thường trú
    private String permanentResidence;

    //dân tộc
    private Integer ethnicId;

    //dân tộc
    private String ethnicName;

    //ton giao
    private String religion;

    //khu vuc tuyen sinh
    private Integer enrollmentArea;

    // đôi tượng ưu tiên
    private Integer priorityType;

    private String educationLevel;

    //chính sách ưu tiên
    private Integer incentivesType;

    //thành phần gia đình
    private String familyElement;

    //ngày vào đoàn
    private LocalDate CYUStartDate;

    //ngày vào đảng
    private LocalDate CPStartDate;

    //thẻ căn cước/CMND
    private String identityNumber;

    //thẻ căn cước/CMND ngày cấp
    private LocalDate identityCreatedDate;

    //thẻ căn cước/CMND nơi cấp
    private String identityCreatedPlace;

    //số tài khoản ngân hàng
    private String bankNumber;

    private String email;

    private String phoneNumber;

    private String fatherName;

    private Integer fatherDateBirth;

    private String fatherWork;

    private String motherName;

    private Integer motherDateBirth;

    private String motherWork;

    //địa chỉ liên lạc
    private String contactAddress;

    private String note;

    private String avatar;

    private String departmentId;

    private String departmentName;

    private String yearClassId;

    private String yearClassName;

    private String branchId;

    private String branchName;

    private Integer courseNumber;

    private Integer status;

    //số báo danh
    private String enrollId;

    //hình thức xét tuyển Admission
    private Integer admissionType;

    private Integer startYear;

    private Integer endYear;

//    public Student toModel() throws ParseException {
//        Student student = new Student();
//        student.setStudentId(this.studentId);
//        student.setFullName(this.fullName);
//        student.setSex(this.sex);
//        student.setDateBirth(Date.valueOf(this.dateBirth));
//        student.setNickName(this.nickName);
//        student.setHomeTown(this.homeTown);
//        student.setPermanentResidence(this.permanentResidence);
//        student.setEthnicId(this.ethnicId);
//        student.setReligion(this.religion);
//        student.setEnrollmentArea(this.enrollmentArea);
//        student.setPriorityType(this.priorityType);
//        student.setIncentivesType(this.incentivesType);
//        student.setFamilyElement(this.familyElement);
//        student.setCYUStartDate(this.CYUStartDate);
//        student.setCPStartDate(this.CPStartDate);
//        student.setIdentityNumber(this.identityNumber);
//        student.setIdentityCreatedDate(this.identityCreatedDate);
//        student.setIdentityCreatedPlace(this.identityCreatedPlace);
//        student.setBankNumber(this.bankNumber);
//        student.setEmail(this.email);
//        student.setPhoneNumber(this.phoneNumber);
//        student.setFatherName(this.fatherName);
//        student.setFatherDateBirth(this.fatherDateBirth);
//        student.setFatherWork(this.fatherWork);
//        student.setMotherName(this.motherName);
//        student.setMotherDateBirth(this.motherDateBirth);
//        student.setMotherWork(this.motherWork);
//        return student;
//    }


}
