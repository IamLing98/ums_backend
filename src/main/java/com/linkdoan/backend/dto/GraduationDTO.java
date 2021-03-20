package com.linkdoan.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GraduationDTO {

    private Long id;

    private String schoolYearId;

    private Integer number;

    public GraduationDTO(Long id, String schoolYearId, Integer number) {
        this.id = id;
        this.schoolYearId = schoolYearId;
        this.number = number;
    }
}
