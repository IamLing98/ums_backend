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

@Entity(name="class")
@Table(name = "class")
@Data
public class Class {

    @Id
    @Column(name = "class_id",unique = true,columnDefinition="CHAR(6)")
    private String classId;

    @Column(name = "class_name" )
    private String className;

    @Column(name = "total_member" )
    private Integer totalMember;

    @Column(name = "year_start" )
    private Integer yearStart; // date type

    @Column(name = "course_number", columnDefinition="Int")
    private Integer courseNumber;

    @Column(name = "adviser_id" )
    private String adviserId;

    @Column(name = "next_val", columnDefinition="Int")
    private Integer nextVal;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="department_id", nullable=false,insertable = false, updatable = false)
    //@JsonBackReference// quat the fackkkkk, cai nay lam treo sys dcmm
    @JsonIgnore
    private Department department;



    public ClassDTO toDTO(){
        ClassDTO classDTO = new ClassDTO();
        classDTO.setClassId(this.classId);
        classDTO.setClassName(this.className);
        classDTO.setTotalMember(this.totalMember.toString());
        classDTO.setYearStart(this.yearStart);
        classDTO.setDepartmentId(department.getDepartmentId());
        classDTO.setAdviserId(this.adviserId);
        classDTO.setCourseNumber(this.courseNumber);
        return classDTO;
    }
}
