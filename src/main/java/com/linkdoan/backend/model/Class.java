package com.linkdoan.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linkdoan.backend.dto.ClassDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "class")
@Data
public class Class {

    @Id
    @Column(name = "class_id", unique = true, columnDefinition = "CHAR(6)")
    private String classId;

    @Column(name = "class_name")
    private String className;

    @Column(name = "total_member")
    private Integer totalMember;

    @Column(name = "year_start")
    private Integer yearStart; // date type

    @Column(name = "course_number", columnDefinition = "Int")
    private Integer courseNumber;

    @Column(name = "adviser_id")
    private String adviserId;

    @Column(name = "next_val", columnDefinition = "Int")
    private Integer nextVal;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    //@JsonBackReference// quat the fackkkkk, cai nay lam treo sys dcmm
    @JsonIgnore
    private Department department;

    public String getDepartmentId() {
        return department.getDepartmentId();
    }

    public void setDepartmentId(String departmentId) {
        this.department.setDepartmentId(departmentId);
    }

    @JsonIgnore
    public Department getDepartment() {
        return department;
    }

    @JsonIgnore
    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDepartmentName() {
        return department.getDepartmentName();
    }

    public ClassDTO toDTO() {
        ClassDTO classDTO = new ClassDTO();
        classDTO.setClassId(this.classId);
        classDTO.setClassName(this.className);
        classDTO.setTotalMember(this.totalMember.toString());
        classDTO.setYearStart(this.yearStart.toString());
        classDTO.setDepartmentId(department.getDepartmentId());
        classDTO.setAdviserId(this.adviserId);
        classDTO.setCourseNumber(this.courseNumber.toString());
        return classDTO;
    }
}
