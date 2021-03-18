package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.model.YearClass;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class StudentDTO {

    private String studentId;

    private String fullName;

    private Integer sex;

    private LocalDate dateBirth;

    private String nickName;

    private String homeTown;

    //quốc tịch
    private String nationality;

    // nơi sinh
    private String bornPlace;

    //hộ khẩu thường trú
    private String permanentResidence;

    //dân tộc
    private String ethnic;

    //ton giao
    private String religion;

    //khu vuc tuyen sinh
    private Integer enrollmentArea;

    // đôi tượng ưu tiên
    private Integer priorityType;

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

    private String educationProgramId;

    //currentTerm
    private Integer currentTerm;

    private LocalDate createdDate;

    YearClass yearClass;

    public Student toModel() {
        Student student = new Student();
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
        student.setCYUStartDate(this.CYUStartDate);
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
        student.setEducationProgramId(this.educationProgramId);
        student.setYearClassId(this.yearClassId);
        student.setCreatedDate(this.createdDate);
        student.setContactAddress(this.contactAddress);
        return student;

    }

    public StudentDTO(String studentId, String fullName, YearClass yearClass) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.yearClass = yearClass;
    }

    public StudentDTO(String studentId, String fullName, String departmentId, String departmentName,
                      String yearClassId, String yearClassName, Integer startYear, Integer endYear, Integer courseNumber) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.yearClassId = yearClassId;
        this.yearClassName = yearClassName;
        this.startYear = startYear;
        this.endYear = endYear;
        this.courseNumber = courseNumber;
    }

    public StudentDTO(String studentId, String fullName, String departmentId, String departmentName,
                      String yearClassId, String yearClassName, Integer startYear, Integer endYear, Integer courseNumber, LocalDate dateBirth) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.yearClassId = yearClassId;
        this.yearClassName = yearClassName;
        this.startYear = startYear;
        this.endYear = endYear;
        this.courseNumber = courseNumber;
        this.dateBirth = dateBirth;
    }

    public StudentDTO(String studentId, String yearClassId, String educationProgramId, Integer currentTerm) {
        this.studentId = studentId;
        this.yearClassId = yearClassId;
        this.educationProgramId = educationProgramId;
        this.currentTerm = currentTerm;
    }

    public StudentDTO(String studentId, String fullName, Integer sex, LocalDate dateBirth, String nickName,
                      String homeTown, String nationality, String bornPlace, String permanentResidence,
                      String ethnic, String religion, Integer enrollmentArea, Integer priorityType,
                      Integer incentivesType, String familyElement, LocalDate CYUStartDate, LocalDate CPStartDate,
                      String identityNumber, LocalDate identityCreatedDate, String identityCreatedPlace,
                      String bankNumber, String email, String phoneNumber, String fatherName, Integer fatherDateBirth,
                      String fatherWork, String motherName, Integer motherDateBirth, String motherWork,
                      String contactAddress, String note, String avatar, String departmentId, String departmentName,
                      String yearClassId, String yearClassName, String branchId, String branchName, Integer courseNumber,
                      Integer status, String enrollId, Integer admissionType, Integer startYear, Integer endYear,
                      String educationProgramId, Integer currentTerm) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.sex = sex;
        this.dateBirth = dateBirth;
        this.nickName = nickName;
        this.homeTown = homeTown;
        this.nationality = nationality;
        this.bornPlace = bornPlace;
        this.permanentResidence = permanentResidence;
        this.ethnic = ethnic;
        this.religion = religion;
        this.enrollmentArea = enrollmentArea;
        this.priorityType = priorityType;
        this.incentivesType = incentivesType;
        this.familyElement = familyElement;
        this.CYUStartDate = CYUStartDate;
        this.CPStartDate = CPStartDate;
        this.identityNumber = identityNumber;
        this.identityCreatedDate = identityCreatedDate;
        this.identityCreatedPlace = identityCreatedPlace;
        this.bankNumber = bankNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fatherName = fatherName;
        this.fatherDateBirth = fatherDateBirth;
        this.fatherWork = fatherWork;
        this.motherName = motherName;
        this.motherDateBirth = motherDateBirth;
        this.motherWork = motherWork;
        this.contactAddress = contactAddress;
        this.note = note;
        this.avatar = avatar;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.yearClassId = yearClassId;
        this.yearClassName = yearClassName;
        this.branchId = branchId;
        this.branchName = branchName;
        this.courseNumber = courseNumber;
        this.status = status;
        this.enrollId = enrollId;
        this.admissionType = admissionType;
        this.startYear = startYear;
        this.endYear = endYear;
        this.educationProgramId = educationProgramId;
        this.currentTerm = currentTerm;
    }
}
