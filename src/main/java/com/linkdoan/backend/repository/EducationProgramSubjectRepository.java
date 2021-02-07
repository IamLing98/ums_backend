package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.EducationProgramSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationProgramSubjectRepository extends JpaRepository<EducationProgramSubject, Long> {

    Optional<EducationProgramSubject> findFirstBySubjectIdAndAndEducationProgramId(String subjectId, String educationProgramId);

    @Query(value =
            "SELECT distinct subject.departmentId, subject.discussNumber, subject.eachSubject, subject.exerciseNumber, " +
            "subject.practiceNumber, subject.selfLearningNumber, subject.subjectForLevel, subject.subjectId, subject.subjectName, " +
            "subject.theoryNumber, department.departmentName, epd.term, epd.id  " +
            "FROM Subject subject  INNER JOIN Department department ON subject.departmentId = department.departmentId " +
            "INNER JOIN EducationProgramSubject epd ON subject.subjectId = epd.subjectId " +
            "WHERE epd.educationProgramId = :educationProgramId "
    )
    List<Object[]> getCorrectListSubjectByEp(@Param("educationProgramId") String educationProgramId);

    @Query(value =
            "SELECT distinct  subject.departmentId, subject.discussNumber, subject.eachSubject, subject.exerciseNumber, " +
            "subject.practiceNumber, subject.selfLearningNumber, subject.subjectForLevel, subject.subjectId, subject.subjectName, " +
            "subject.theoryNumber " +
            "FROM Subject subject  " +
            "WHERE  not subject.subjectId   in :listSubjectId "
    )
    List<Object[]> getListSubjectNotInEducation(@Param("listSubjectId") List<String> listSubjectId);
}
