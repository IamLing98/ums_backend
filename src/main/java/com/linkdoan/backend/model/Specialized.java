package com.linkdoan.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linkdoan.backend.dto.SpecializedDTO;
import lombok.Data;

import javax.persistence.*;

@Table(name = "specialized")
@Data
@Entity(name = "specialized")

public class Specialized {
    @Id
    @Column(name = "specialized_id", columnDefinition = "char(10)", unique = true)
    private String specializedId;

    @Column(name = "specialized_name", columnDefinition = "varchar(100)")
    private String specializedName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    //@JsonBackReference// quat the fackkkkk, cai nay lam treo sys dcmm
    @JsonIgnore
    private Department department;

    public String getDepartmentId() {
        return department.getDepartmentId();
    }

    public void setDepartmentId(String departmentId) {
        this.department.setDepartmentId(departmentId);
    }

    @JsonIgnore
    public Department getDepartment() {
        return department;
    }

    @JsonIgnore
    public void setDepartment(Department department) {
        this.department = department;
    }

    public SpecializedDTO toDTO() {
        SpecializedDTO specializedDTO = new SpecializedDTO();
        specializedDTO.setSpecializedId(this.specializedId);
        specializedDTO.setSpecializedName(this.specializedName);
        specializedDTO.setDepartmentId(this.getDepartmentId());
        return specializedDTO;
    }
}
