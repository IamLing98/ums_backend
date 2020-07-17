package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.linkdoan.backend.model.Class;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDTO extends SystemDTO {
    private String classId;

    @AdjHistory(field = "className" )
    private String className;

    @AdjHistory(field = "totalMember" )
    private String totalMember;

    @AdjHistory(field = "yearStart" )
    private Integer yearStart; //date type

    @AdjHistory(field = "courseNumber" )
    private Integer courseNumber;

    @AdjHistory(field = "departmentId" )
    private String departmentId;

    @AdjHistory(field = "adviserId" )
    private String adviserId;


    public Class toModel(Department department){
        Class classModel = new Class();
        classModel.setClassId(this.classId);
        classModel.setAdviserId(this.adviserId);
        classModel.setClassName(this.className);
        classModel.setTotalMember(this.totalMember);
        classModel.setYearStart(this.yearStart);
        classModel.setDepartment(department);
        classModel.setCourseNumber(this.courseNumber);
        return classModel;
    }
}
