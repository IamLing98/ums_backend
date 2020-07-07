package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.ClassDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "class")
@Data
public class Class {
    @Id
    @Column(name = "class_id",unique = true)
    private String classId;

    @Column(name = "class_name" )
    private String className;

    @Column(name = "total_member" )
    private String totalMember;

    @Column(name = "year_start" )
    private Date yearStart;

    @Column(name = "department_id" )
    private String departmentId;

    @Column(name = "adviser_id" )
    private String adviserId;

    public ClassDTO toDTO(){
        ClassDTO classDTO = new ClassDTO();
        classDTO.setClassId(classId);
        classDTO.setClassName(className);
        classDTO.setTotalMember(totalMember);
        classDTO.setYearStart(yearStart);
        classDTO.setDepartmentId(departmentId);
        classDTO.setAdviserId(adviserId);
        return classDTO;
    }
}
