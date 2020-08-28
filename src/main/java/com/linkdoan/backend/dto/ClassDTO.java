package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Class;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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

    @NotNull
    @AdjHistory(field = "courseNumber" )
    private String courseNumber;

    @NotBlank
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
