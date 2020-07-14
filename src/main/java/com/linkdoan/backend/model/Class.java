package com.linkdoan.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.linkdoan.backend.dto.ClassDTO;
import com.linkdoan.backend.repository.DepartmentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "class")
@Data
public class Class {

    @Id
    @Column(name = "class_id",unique = true,columnDefinition="CHAR(6)")
    private String classId;

    @Column(name = "class_name" )
    private String className;

    @Column(name = "total_member" )
    private String totalMember;

    @Column(name = "year_start" )
    private Integer yearStart; // date type

    @Column(name = "course_number", columnDefinition="Int")
    private Integer courseNumber;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="department_id", nullable=false)
    //@JsonBackReference quat the fackkkkk, cai nay lam treo sys dcmm
    @JsonIgnore
    private Department department;


    @Column(name = "adviser_id" )
    private String adviserId;



    public ClassDTO toDTO(){
        ClassDTO classDTO = new ClassDTO();
        classDTO.setClassId(this.classId);
        classDTO.setClassName(this.className);
        classDTO.setTotalMember(this.totalMember);
        classDTO.setYearStart(this.yearStart);
        classDTO.setDepartmentId(department.getDepartmentId());
        classDTO.setAdviserId(this.adviserId);
        classDTO.setCourseNumber(this.courseNumber);
        return classDTO;
    }
}
