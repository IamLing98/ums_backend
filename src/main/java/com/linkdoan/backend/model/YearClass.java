package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.YearClassDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="year_class")
@Data
public class YearClass {

    @Id
    @Column(name = "class_id", unique = true, columnDefinition = "CHAR(6)")
    private String classId;

    @Column(name = "class_name")
    private String className;

    @Column(name = "total_member")
    private Integer totalMember;

    @Column(name = "start_year")
    private Integer startYear; // date type

    @Column(name = "endYear")
    private Integer endYear;

    @Column(name = "course_number", columnDefinition = "Int")
    private Integer courseNumber;

    @Column(name = "adviser_id")
    private String adviserId;

    @Column(name = "next_val", columnDefinition = "Int")
    private Integer nextVal;

    @NotNull
    @Column(name = "branch_id", nullable = false, columnDefinition = "CHAR(10)")
    private String branchId;

    public YearClassDTO toDTO() {
        YearClassDTO yearClassDTO = new YearClassDTO();
        yearClassDTO.setClassId(this.classId);
        yearClassDTO.setClassName(this.className);
        yearClassDTO.setTotalMember(this.totalMember);
        yearClassDTO.setStartYear(this.startYear);
        //classYearDTO.setBranchId(this.branchId);
        yearClassDTO.setAdviserId(this.adviserId);
        yearClassDTO.setCourseNumber(this.courseNumber);
        return yearClassDTO;
    }
}
