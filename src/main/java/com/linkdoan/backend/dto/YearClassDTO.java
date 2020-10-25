package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.YearClass;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class YearClassDTO {

    private String classId;

    private String className;

    private Integer totalMember;

    private Integer startYear; // date type

    private Integer endYear;

    private Integer courseNumber;

    private String adviserId;

    private Integer nextVal;

    private String branchId;

    private String branchName;

    private String departmentId;

    private String departmentName;

    private Integer educationProgramLevel;

    private Integer educationProgramType;

    private List<StudentDTO> studentList;

    private String teacherId;

    private String teacherFullName;

    public YearClass toModel() {
        YearClass classModel = new YearClass();
        classModel.setClassId(this.classId);
        classModel.setAdviserId(this.adviserId);
        classModel.setClassName(this.className);
        return classModel;
    }

    public YearClassDTO(String classId, String className, Integer totalMember, Integer startYear, Integer endYear,
                        Integer courseNumber, String adviserId, Integer nextVal, String branchId, String branchName,
                        String departmentId, String departmentName, Integer educationProgramLevel, Integer educationProgramType,
                        String teacherId, String teacherFullName) {
        this.classId = classId;
        this.className = className;
        this.totalMember = totalMember;
        this.startYear = startYear;
        this.endYear = endYear;
        this.courseNumber = courseNumber;
        this.adviserId = adviserId;
        this.nextVal = nextVal;
        this.branchId = branchId;
        this.branchName = branchName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.educationProgramLevel = educationProgramLevel;
        this.educationProgramType = educationProgramType;
        this.teacherId = teacherId;
        this.teacherFullName = teacherFullName;
    }
}
