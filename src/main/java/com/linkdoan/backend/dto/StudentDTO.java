package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO  extends SystemDTO {
    private String studentId;

    @AdjHistory(field = "firstName")
    private String firstName;

    @AdjHistory(field = "lastName")
    private String lastName;

    @AdjHistory(field = "dateBirth")
    private Date dateBirth;

    @AdjHistory(field = "sex")
    private int sex;

    @AdjHistory(field = "nation")
    private String nation;

    @AdjHistory(field = "homeAddress")
    private String homeAddress;

    @AdjHistory(field = "currentAddress")
    private String currentAddress;

    @AdjHistory(field = "email")
    private String email;

    @AdjHistory(field = "homeNumber")
    private String homeNumber;

    @AdjHistory(field = "phoneNumber")
    private String phoneNumber;

    @AdjHistory(field = "fatherName")
    private String fatherName;

    @AdjHistory(field = "fatherDatebirth")
    private Date fatherDatebirth;

    @AdjHistory(field = "fatherPhoneNumber")
    private String fatherPhoneNumber;

    @AdjHistory(field = "fatherWorkWhere")
    private String fatherWorkWhere;

    @AdjHistory(field = "motherName")
    private String motherName;

    @AdjHistory(field = "motherDatebirth")
    private Date motherDatebirth;

    @AdjHistory(field = "motherPhoneNumber")
    private String motherPhoneNumber;

    @AdjHistory(field = "motherWorkWhere")
    private String motherWorkWhere;

    @AdjHistory(field = "startSchool")
    private Date startSchool;

    @AdjHistory(field = "religion")
    private String religion;

    @AdjHistory(field = "avatar")
    private String avatar;


    @AdjHistory(field = "classId")
    private String classId;

    @AdjHistory(field = "departmentId")
    private String departmentId;



    public Student toModel(){
        Student student = new Student();
        student.setStudentId(this.studentId);
        student.setFirstName(this.firstName) ;
        student.setLastName(this.lastName);
        student.setDateBirth(this.dateBirth);
        student.setSex(this.sex);
        student.setNation(this.nation);
        student.setHomeAddress(this.homeAddress);
        student.setCurrentAddress(this.currentAddress);
        student.setEmail(this.email);
        student.setHomeNumber(this.homeNumber);
        student.setPhoneNumber(this.phoneNumber);
        student.setFatherDateBirth(this.fatherDatebirth);
        student.setFatherPhoneNumber(this.fatherPhoneNumber);
        student.setFatherWorkWhere(this.fatherWorkWhere);
        student.setMotherName(this.motherName);
        student.setMotherDateBirth(this.motherDatebirth);
        student.setMotherPhoneNumber(this.motherPhoneNumber);
        student.setMotherWorkWhere(this.motherWorkWhere);
        student.setStartSchool(this.startSchool);
        student.setReligion(this.religion);
        student.setAvatar(this.avatar);
        student.setClassId(this.classId);
        student.setDepartmentId(this.departmentId);

        return student;

    }
}
