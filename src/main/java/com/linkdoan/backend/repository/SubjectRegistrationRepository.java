package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.SubjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRegistrationRepository extends JpaRepository<SubjectRegistration, Long> {

    //for admin
    @Query(value =
            "SELECT new com.linkdoan.backend.dto.SubjectDTO(subject.subjectId, subject.subjectName, subject.eachSubject, " +
                    "subject.theoryNumber, subject.exerciseNumber, subject.discussNumber, subject.selfLearningNumber, subject.practiceNumber, subject.subjectForLevel) " +
                    "FROM SubjectRegistration subjectRegistration INNER JOIN Subject  subject ON subjectRegistration.subjectId = subject.subjectId " +
                    "WHERE (subjectRegistration.studentId = :studentId AND subjectRegistration.termId = :termId ) "
    )
    List<SubjectDTO> getAllByStudentIdAndTermId(@Param("studentId") String studentId, @Param("termId") String termId);

    @Query(value =
            "SELECT subject.subjectId, subject.subjectName , count(subject.subjectId), subject.discussNumber, subject.exerciseNumber, " +
                    "subject.practiceNumber, subject.selfLearningNumber, subject.theoryNumber, department.departmentName,subject.subjectType, " +
                    "subject.eachSubject, subject.discussNumber, subject.subjectForLevel " +
                    "FROM  Subject subject " +
                    "Inner join EducationProgramSubject educationProgramSubject on subject.subjectId = educationProgramSubject.subjectId " +
                    "inner join EducationProgram  educationProgram on educationProgram.educationProgramId = educationProgramSubject.educationProgramId " +
                    "inner join Student student on educationProgram.educationProgramId = student.educationProgramId " +
                    "inner join YearClass yearClass on student.yearClassId = yearClass.classId " +
                    "INNER JOIN Department department ON subject.departmentId = department.departmentId " +
                    "where yearClass.currentTerm + 1 = educationProgramSubject.term group by subject.subjectId "
    )
    List<Object[]> getPredictTotalSubmit(@Param("termId") String termId);

    @Query(value =
            "SELECT  subject.subjectId, subject.subjectName, count(subjectRegistration.subjectId) FROM Subject subject " +
                    "inner join SubjectRegistration subjectRegistration on subject.subjectId = subjectRegistration.subjectId " +
                    "where subjectRegistration.termId = :termId    group by subject.subjectId "
    )
    List<Object[]> getCurrentTotalSubmit(@Param("termId") String termId);

    @Query(value =
            "SELECT  subject.subjectId, subject.subjectName, count(subjectRegistration.subjectId) FROM Subject subject " +
                    "inner join SubjectRegistration subjectRegistration on subject.subjectId = subjectRegistration.subjectId " +
                    "where subjectRegistration.termId = :termId and subjectRegistration.autoSubmit = 1 group by subject.subjectId "
    )
    List<Object[]> getTotalAutoSubmit(@Param("termId") String termId);

    @Query(value =
            "SELECT  subject.subjectId, subject.subjectName, count(subject.subjectId) FROM Subject subject " +
                    "inner join SubjectClass subjectClass on subject.subjectId = subjectClass.subjectId " +
                    "where subjectClass.termId = :termId   group by subject.subjectId "
    )
    List<Object[]> getTotalClassWithSubject(@Param("termId") String termId);

    List<SubjectRegistration> findAllByTermIdAndAutoSubmit(String termId, Integer autoSubmit);

    SubjectRegistration findFirstByStudentIdAndSubjectIdAndTermId(String studentId, String subjectId, String termId);
}
