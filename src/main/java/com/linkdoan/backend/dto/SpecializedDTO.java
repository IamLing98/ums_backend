package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Specialized;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpecializedDTO extends SystemDTO {
    private String specializedId;
    private String specializedName;
    private String branchId;

    public Specialized toModel() {
        Specialized specialized = new Specialized();
        specialized.setBranchId(this.branchId);
        specialized.setSpecializedId(this.specializedId);
        specialized.setSpecializedName(this.specializedName);
        return specialized;
    }
}
