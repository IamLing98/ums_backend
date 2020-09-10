package com.linkdoan.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchDTO {

    private String branchId;

    private String branchName;

    private List<YearClassDTO> yearClassDTOList;

    public BranchDTO(String branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }
}
