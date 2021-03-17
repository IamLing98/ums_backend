package com.linkdoan.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResultDTO {

    Long id;

    String studentId;

    private String termId;

    private Double GPA;

    private Double diemRenLuyen;

}
