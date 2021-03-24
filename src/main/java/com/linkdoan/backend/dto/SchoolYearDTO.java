package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.SchoolYear;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolYearDTO {

    private String schoolYearId;

    private Integer start;

    private Integer end;

    private List<TermDTO> termDTOList;

    public SchoolYear toModel() {
        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setSchoolYearId(schoolYearId);
        schoolYear.setStart(start);
        schoolYear.setEnd(end);
        return schoolYear;
    }
}
