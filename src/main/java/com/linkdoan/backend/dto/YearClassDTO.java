package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Branch;
import com.linkdoan.backend.model.YearClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearClassDTO extends SystemDTO {

    private String classId;

    private String className;

    private Integer totalMember;

    private Integer startYear; // date type

    private Integer endYear;

    private Integer courseNumber;

    private String adviserId;

    private Integer nextVal;

    private Branch branch;


    public YearClass toModel(){
        YearClass classModel = new YearClass();
        classModel.setClassId(this.classId);
        classModel.setAdviserId(this.adviserId);
        classModel.setClassName(this.className);
        return classModel;
    }
}
