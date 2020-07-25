package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.linkdoan.backend.model.Class;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDTO  extends SystemDTO {
    private String classId;

    @AdjHistory(field = "className" )
    private String className;

    @AdjHistory(field = "totalMember" )
    private String totalMember;

    @AdjHistory(field = "yearStart" )
    private String yearStart; //date type

    @AdjHistory(field = "courseNumber" )
    private String courseNumber;

    @AdjHistory(field = "departmentId" )
    private String departmentId;

    @AdjHistory(field = "adviserId" )
    private String adviserId;


    public Class toModel(){
        Class classModel = new Class();
        classModel.setClassId(this.classId);
        classModel.setAdviserId(this.adviserId);
        classModel.setClassName(this.className);
        return classModel;
    }
}
