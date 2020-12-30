package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import com.linkdoan.backend.model.SubjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRegistrationRepository extends JpaRepository<SubjectRegistration, Long> {


    List<SubjectRegistration> getAllByStudentIdAndTermId(String studentId,String termId);

    SubjectRegistration findFirstByStudentIdAndSubjectIdAndTermId(String studentId, String subjectId, String termId);

    Long deleteByStudentIdAndSubjectIdAndTermId(String studentId, String subjectId, String termId);
}
