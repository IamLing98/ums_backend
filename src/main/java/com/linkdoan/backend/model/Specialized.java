package com.linkdoan.backend.model;

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

    @JoinColumn(name = "branch_id", nullable = false)
    //@JsonBackReference// quat the fackkkkk, cai nay lam treo sys dcmm
    private String branchId;

    public SpecializedDTO toDTO() {
        SpecializedDTO specializedDTO = new SpecializedDTO();
        specializedDTO.setSpecializedId(this.specializedId);
        specializedDTO.setSpecializedName(this.specializedName);
        specializedDTO.setBranchId(this.branchId);
        return specializedDTO;
    }
}
