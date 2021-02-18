package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Specialized;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpecializedDTO {

    private String specializedId;

    private String specializedName;

    private String branchId;

    private int page;

    private int pageSize;

    public Specialized toModel() {
        Specialized specialized = new Specialized();
        specialized.setBranchId(this.branchId);
        specialized.setSpecializedId(this.specializedId);
        specialized.setSpecializedName(this.specializedName);
        return specialized;
    }
}
