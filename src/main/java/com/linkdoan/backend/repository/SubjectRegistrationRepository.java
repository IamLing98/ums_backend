package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import com.linkdoan.backend.model.SubjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRegistrationRepository extends JpaRepository<SubjectRegistration, Long> {

    @Query(value = "SELECT new com.linkdoan.backend.dto.SubjectDTO(subject.subjectId, subject.subjectName, subject.eachSubject, subject.theoryNumber, subject.exerciseNumber, subject.discussNumber, subject.selfLearningNumber, subject.practiceNumber, subject.subjectForLevel) " +
            "FROM Subject  subject, SubjectRegistration subjectRegistration " +
            "WHERE (subject.subjectId = subjectRegistration.subjectId AND subjectRegistration.studentId = :studentId AND subjectRegistration.termId = :termId )"
    )
    List<SubjectDTO> getAllByStudentIdAndTermId(@Param("studentId") String studentId, @Param("termId") String termId);

    @Query(value = "SELECT subject.subjectId, subject.subjectName, count(subjectRegistration.subjectId ) as sum   from Subject subject left join SubjectRegistration  subjectRegistration" +
            " on subject.subjectId = subjectRegistration.subjectId " +
            " group by subject.subjectId  ")
    List<Object[]> getSubmittingInfo(@Param("termId") String termId);


    SubjectRegistration findFirstByStudentIdAndSubjectIdAndTermId(String studentId, String subjectId, String termId);

    Long deleteByStudentIdAndSubjectIdAndTermId(String studentId, String subjectId, String termId);
}
