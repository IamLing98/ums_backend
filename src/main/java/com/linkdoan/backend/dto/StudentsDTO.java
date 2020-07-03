package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.model.StudentsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsDTO    {
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



    public StudentsModel toModel(){
        StudentsModel studentModel = new StudentsModel();

        return studentModel;

    }
}
