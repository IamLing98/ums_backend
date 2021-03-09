package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.dto.FileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectClassRegistrationDTO extends FileDTO {

    private Long id;

    private String studentId;

    private String subjectClassId;

    private String subjectId;

    private String termId;

    private Integer autoSubmit;

    private Long scheduleId;

    private Long progressSubmitted;
}
