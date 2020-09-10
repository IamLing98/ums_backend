package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.EducationProgramSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationProgramSubjectRepository extends JpaRepository<EducationProgramSubject, Long> {

    List<EducationProgramSubject> findAllByEducationProgramId(String educationId);

    Integer deleteAllByEducationProgramId(String eduProId);

    Integer deleteByEducationProgramIdAndSubjectId(String eduProId, String subjectId);

    Integer deleteAllBySubjectId(String subjectId);
}
