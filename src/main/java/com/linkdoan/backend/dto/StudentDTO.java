package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO  extends SystemDTO {
    @AdjHistory(field = "firstName")
    private String studentId;

    @AdjHistory(field = "firstName")
    private String firstName;

    @AdjHistory(field = "lastName")
    private String lastName;

    @AdjHistory(field = "fullName")
    private String fullName;

    @AdjHistory(field = "dateBirth")
    private String dateBirth;

    @AdjHistory(field = "sex")
    private String sex = "";

    @AdjHistory(field = "nationality")
    private String nationality;

    private String ethnic;

    @AdjHistory(field = "homeAddress")
    private String homeAddress;

    @AdjHistory(field = "currentAddress")
    private String currentAddress;

    @AdjHistory(field = "email")
    private String email;

    @AdjHistory(field = "phoneNumber")
    private String phoneNumber;

    @AdjHistory(field = "fatherName")
    private String fatherName;

    @AdjHistory(field = "fatherDateBirth")
    private String fatherDateBirth = "";

    @AdjHistory(field = "fatherWork")
    private String fatherWork;

    @AdjHistory(field = "motherName")
    private String motherName;

    @AdjHistory(field = "motherDateBirth")
    private String motherDateBirth = "";

    @AdjHistory(field = "motherWork")
    private String motherWork;

    @AdjHistory(field = "familyPhoneNumber")
    private String familyPhoneNumber;

    @AdjHistory(field = "startSchool")
    private String startSchool = "";

    @AdjHistory(field = "religion")
    private String religion;

    @AdjHistory(field = "avatar")
    private String avatar;

    @AdjHistory(field = "classId")
    private String classId;

    @AdjHistory(field = "departmentId")
    private String departmentId;

    @AdjHistory(field = "status")
    private String status;

    public Student toModel() throws ParseException {
        Student student = new Student();
        student.setStudentId(this.studentId);
        student.setFirstName(this.firstName) ;
        student.setLastName(this.lastName);
        student.setFullName(this.fullName);
        if(this.dateBirth != "") {
            Date javaDatetime = new SimpleDateFormat("yyyy-MM-dd").parse(this.getDateBirth());
            student.setDateBirth(new java.sql.Date(javaDatetime.getTime()));
        }
        if(StringUtils.isNumeric(this.sex) ) student.setSex(Integer.parseInt(this.sex));
        student.setNation(this.nationality);
        student.setHomeAddress(this.homeAddress);
        student.setCurrentAddress(this.currentAddress);
        student.setEmail(this.email);
        student.setFamilyPhoneNumber(this.familyPhoneNumber);
        student.setPhoneNumber(this.phoneNumber);
        student.setFatherName(this.fatherName);
        if(StringUtils.isNumeric(this.fatherDateBirth)) student.setFatherDateBirth(Integer.parseInt(this.fatherDateBirth));
        student.setFatherWork(this.fatherWork);
        student.setMotherName(this.motherName);
        if(StringUtils.isNumeric(this.motherDateBirth)) student.setMotherDateBirth(Integer.parseInt(this.motherDateBirth));
        student.setMotherWork(this.motherWork);
        student.setReligion(this.religion);
        student.setAvatar(this.avatar);
        student.setClassId(this.classId);
        student.setDepartmentId(this.departmentId);
        student.setEthnic(this.ethnic);
        return student;

    }
}
