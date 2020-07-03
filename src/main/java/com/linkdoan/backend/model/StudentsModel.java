package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.StudentsDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
@Data
public class StudentsModel {
    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "date_birth")
    private Date dateBirth;


    @Column(name = "sex")
    private int sex;

    @Column(name = "nation")
    private String nation;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "current_address")
    private String currentAddress;

    @Column(name = "email")
    private String email;

    @Column(name = "home_number")
    private String homeNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "father_date_birth")
    private Date fatherDateBirth;

    @Column(name = "father_phone_number")
    private String fatherPhoneNumber;

    @Column(name = "father_work_where")
    private String fatherWorkWhere;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "mother_date_birth")
    private Date motherDateBirth;

    @Column(name = "mother_phone_number")
    private String motherPhoneNumber;

    @Column(name = "mother_work_where")
    private String motherWorkWhere;

    @Column(name = "start_school")
    private Date startSchool;

    @Column(name = "religion")
    private String religion;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "class_id")
    private String classId;

    @Column(name = "department_id")
    private String departmentId;

    public StudentsDTO toDTO(){
        StudentsDTO studentsDTO = new StudentsDTO();

        return studentsDTO;
    }
}
