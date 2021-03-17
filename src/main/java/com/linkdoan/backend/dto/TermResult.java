package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Term;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TermResult {

    Long id;

    String termId;

    Term term;

    Double GPA;

    Integer diemRenLuyen;

    Integer rank;

    List<SubjectClassRegistrationDTO> subjectClassRegistrationList;

}
