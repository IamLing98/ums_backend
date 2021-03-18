package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.YearClassDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "year_class")
@Data
public class YearClass {

    @Id
    @Column(name = "class_id", unique = true, columnDefinition = "CHAR(6)")
    private String classId;

    @Column(name = "class_name")
    private String className;

    @Column(name = "total_member")
    private Integer totalMember = 0;

    @Column(name = "start_year", columnDefinition = "INT")
    private Integer startYear; // date type

    @Column(name = "endYear", columnDefinition = "INT")
    private Integer endYear;

    //khoa' bao nhieu
    @Column(name = "course_number", columnDefinition = "Int")
    private Integer courseNumber;

    @Column(name = "education_program_level", columnDefinition = "INT")
    private Integer educationProgramLevel;

    @Column(name = "education_program_type", columnDefinition = "INT")
    private Integer educationProgramType = 1;

    @Column(name = "teacher_id" )
    private String teacherId;

    //ky hoc hien tai
    @Column(name = "current_term", columnDefinition = "INT")
    private Integer currentTerm = 1;

    @Column(name = "department_id", columnDefinition = "CHAR(7)")
    private String departmentId;

    public YearClassDTO toDTO() {
        YearClassDTO yearClassDTO = new YearClassDTO();
        yearClassDTO.setClassId(this.classId);
        yearClassDTO.setClassName(this.className);
        yearClassDTO.setTotalMember(this.totalMember);
        yearClassDTO.setStartYear(this.startYear);
        yearClassDTO.setCourseNumber(this.courseNumber);
        yearClassDTO.setDepartmentId(this.departmentId);
        return yearClassDTO;
    }


}
