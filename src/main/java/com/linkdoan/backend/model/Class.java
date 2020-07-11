package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.ClassDTO;
import com.linkdoan.backend.repository.DepartmentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name="department_id", nullable=false)
    private Department department;

    @Column(name = "adviser_id" )
    private String adviserId;

    public ClassDTO toDTO(){
        ClassDTO classDTO = new ClassDTO();
        classDTO.setClassId(classId);
        classDTO.setClassName(className);
        classDTO.setTotalMember(totalMember);
        classDTO.setYearStart(yearStart);
        classDTO.setDepartmentId(department.getDepartmentId());
        classDTO.setAdviserId(adviserId);
        return classDTO;
    }
}
