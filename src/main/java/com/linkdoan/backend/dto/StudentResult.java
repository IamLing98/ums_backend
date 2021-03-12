package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.model.StudentSubject;
import com.linkdoan.backend.model.YearClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResult {

    Student student;

    YearClass yearClass;

    EducationProgramDTO educationProgramDTO;

    List<TermResult> resultDTOs;
}
