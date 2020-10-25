package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import com.linkdoan.backend.model.SubjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRegistrationRepository extends JpaRepository<SubjectRegistration, Integer> {

    @Query(value = "SELECT distinct new com.linkdoan.backend.dto.SubjectRegistrationDTO(" +
            "subject.subjectId, subject.subjectName, subject.eachSubject,  subject.theoryNumber, subject.exerciseNumber, subject.discussNumber," +
            "subject.selfLearningNumber, subject.practiceNumber, subject.subjectForLevel, subjectRegistration.id, subjectRegistration.term, subjectRegistration.date " +
            ") " +
            "FROM Subject  subject inner join SubjectRegistration subjectRegistration on subject.subjectId = subjectRegistration.subjectId " +
            " inner join Student student on student.studentId = subjectRegistration.studentId " +
            " inner join Term term on term.id = subjectRegistration.termId " +
            "WHERE :studentId is null or :studentId ='' or subjectRegistration.studentId = :studentid " +
            "and :termId is null or :termId = '' or subjectRegistration.termId = :termId"
    )
    List<SubjectRegistrationDTO> getListSubjectRegistrationByStudentIdAndTermId(@Param("studentId") String studentId, @Param("termId") String termId);

    SubjectRegistration findFirstByStudentIdAndDateAndSubjectIdAndTermId(String studentId, String subjectId, String termId);

    Long deleteByStudentIdAndSubjectIdAndTermId(String studentId, String subjectId, String termId);
}
