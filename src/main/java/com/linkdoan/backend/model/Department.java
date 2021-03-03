package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.DepartmentDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "department_id", unique = true, columnDefinition = "CHAR(7)")
    private String departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_type")
    private Integer departmentType;

    @Column(name = "start_year")
    private Integer startYear; //date type


    public DepartmentDTO toDTO() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(this.departmentId);
        departmentDTO.setDepartmentName(this.departmentName);
        departmentDTO.setDepartmentType(this.departmentType );
        departmentDTO.setStartYear(this.startYear );
        return departmentDTO;
    }

}
