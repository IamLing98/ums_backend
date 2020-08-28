package com.linkdoan.backend.model;

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

    @JoinColumn(name = "department_id", nullable = false, columnDefinition = "CHAR(7)")
    private String departmentId;

    public ClassDTO toDTO() {
        ClassDTO classDTO = new ClassDTO();
        classDTO.setClassId(this.classId);
        classDTO.setClassName(this.className);
        classDTO.setTotalMember(this.totalMember.toString());
        classDTO.setYearStart(this.yearStart.toString());
        classDTO.setDepartmentId(this.departmentId);
        classDTO.setAdviserId(this.adviserId);
        classDTO.setCourseNumber(this.courseNumber.toString());
        return classDTO;
    }
}
