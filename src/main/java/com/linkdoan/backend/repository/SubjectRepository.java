package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.EducationProgramSubjectDTO;
import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subjectRepository")
public interface SubjectRepository extends JpaRepository<Subject, String> {

    @Query(value = "SELECT new com.linkdoan.backend.dto.SubjectDTO(subject.subjectId, subject.subjectName )  FROM Subject  subject " +
            "inner join  EducationProgramSubject educationProgramSubject on subject.subjectId = educationProgramSubject.subjectId " +
            "inner join  EducationProgram educationProgram on educationProgramSubject.educationProgramId = educationProgram.educationProgramId " +
            "WHERE educationProgram.educationProgramId = :educationProgramId and  educationProgramSubject.term = :term ")
    List<SubjectDTO> findAllByEducationProgramIdAndTerm(@Param("educationProgramId") String educationProgramId, @Param("term") Integer term);

    @Query(value = "SELECT new com.linkdoan.backend.dto.SubjectDTO(subject.subjectId, subject.subjectName )  FROM Subject  subject " +
            "inner join  EducationProgramSubject educationProgramSubject on subject.subjectId = educationProgramSubject.subjectId " +
            "inner join  EducationProgram educationProgram on educationProgramSubject.educationProgramId = educationProgram.educationProgramId ")
    List<SubjectDTO> getAllSubject();

    @Query(value = "SELECT  sb.subjectId, sb.subjectName  " +
            "FROM PrerequisitesSubject ps inner join Subject sb ON ps.prerequisitesSubjectId = sb.subjectId " +
            "WHERE ps.subjectId = :subjectId"
    )
    List<Object[]> getPreviousLearnSubject(@Param("subjectId") String subjectId);

    @Query(value = "SELECT subject.departmentId, subject.discussNumber, subject.eachSubject, subject.exerciseNumber" +
            " ,subject.practiceNumber, subject.selfLearningNumber, subject.subjectForLevel, subject.subjectId, subject.subjectName, " +
            "subject.theoryNumber, department.departmentName " +
            "FROM Subject subject left join Department department ON subject.departmentId = department.departmentId "
    )
    List<Object[]> getAll();

    @Query(value = "SELECT distinct eps.subjectId " +
            "FROM EducationProgramSubject eps " +
            "WHERE eps.educationProgramId = :educationProgramId  "
    )
    List<String> getListSubjectIdByEducationProgramId(@Param("educationProgramId") String educationProgramId);


}
