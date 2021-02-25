package com.linkdoan.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeeCategoryDTO {

    private Long id;

    private String feeCategoryName;

    private String feeCategoryGroupName;

    private String description;

    private Long value;
}
