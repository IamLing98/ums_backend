package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.YearClass;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class YearClassDTO {

    private String classId;

    private String className;

    private Integer totalMember;

    private Integer startYear; // date type

    private Integer endYear;

    private Integer courseNumber;

    private Integer educationProgramLevel;

    private Integer educationProgramType ;

    private String teacherId;

    private String teacherFullName;

    private Integer currentTerm = 1;

    private String departmentId;

    private String departmentName;

    //private List<StudentDTO> studentDTOList = new ArrayList<>();


    public YearClassDTO(String classId, String className, Integer totalMember, Integer startYear, Integer endYear,
                        Integer courseNumber, Integer educationProgramLevel, Integer educationProgramType,
                        String teacherId, String teacherFullName, Integer currentTerm, String departmentId, String departmentName) {
        this.classId = classId;
        this.className = className;
        this.totalMember = totalMember;
        this.startYear = startYear;
        this.endYear = endYear;
        this.courseNumber = courseNumber;
        this.educationProgramLevel = educationProgramLevel;
        this.educationProgramType = educationProgramType;
        this.teacherId = teacherId;
        this.teacherFullName = teacherFullName;
        this.currentTerm = currentTerm;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public YearClassDTO(String yearClss){
        this.classId = yearClss;
    }

    public YearClass toModel() {
        YearClass yearClass = new YearClass();
        yearClass.setClassName(this.classId);
        return yearClass;
    }


}
