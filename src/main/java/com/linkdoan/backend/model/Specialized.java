package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.SpecializedDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "specialized")
@Data
@Entity(name = "specialized")

public class Specialized {
    @Id
    @Column(name="specialized_id", columnDefinition = "char(10)",unique = true)
    private String specializedId;

    @Column(name="specialized_name", columnDefinition = "varchar(100)")
    private String specializedName;

    @Column(name="department_id", columnDefinition = "char(10)")
    private String departmentId;

    public SpecializedDTO toDTO(){
        SpecializedDTO specializedDTO = new SpecializedDTO();
        specializedDTO.setSpecializedId(this.specializedId);
        specializedDTO.setSpecializedName(this.specializedName);
        specializedDTO.setDepartmentId(this.departmentId);
        return specializedDTO;
    }
}
