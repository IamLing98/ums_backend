package com.linkdoan.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectClassRegistrationDTO {

    private Long id;

    private String studentId;

    private String subjectClassId;

    private String subjectId;

    private String termId;

    private Integer autoSubmit;

    private Long scheduleId;

    private Long progressSubmitted;
}
