package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.EducationProgramSubjectDTO;
import com.linkdoan.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subjectRepository")
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Subject findBySubjectId(String subjectId);

    @Query(value = "SELECT new com.linkdoan.backend.dto.EducationProgramSubjectDTO(subject.subjectId, subject.subjectName, subject.eachSubject, subject.theoryNumber," +
            "subject.exerciseNumber, subject.discussNumber, subject.selfLearningNumber, subject.practiceNumber," +
            "subject.subjectForLevel,   educationProgramSubject.transactionType, educationProgramSubject.term) " +
            "FROM Subject  subject, EducationProgramSubject educationProgramSubject " +
            "WHERE (subject.subjectId = educationProgramSubject.subjectId AND educationProgramSubject.educationProgramId = :educationProgramId )"
    )
    List<EducationProgramSubjectDTO> findAllByEducationProgramId(@Param("educationProgramId") String Id);

    Subject findFirstBySubjectId(String subjectId);

//    @Query(value = "SELECT distinct new com.linkdoan.backend.dto.EducationProgramSubjectDTO(subject.subjectId, subject.subjectName, subject.eachSubject, subject.theoryNumber," +
//            "subject.exerciseNumber, subject.discussNumber, subject.selfLearningNumber, subject.practiceNumber," +
//            "subject.subjectForLevel,   yearClassSubject.transactionType, yearClassSubject.term) " +
//            "FROM Subject  subject INNER JOIN YearClassSubject yearClassSubject on subject.subjectId = yearClassSubject.subjectId " +
//            "inner join YearClass yearClass on yearClassSubject.yearClassId = yearClass.classId " +
//            "WHERE (yearClass.classId = yearClassSubject.yearClassId and yearClassSubject.subjectId = subject.subjectId and  :yearClassId is null or :yearClassId ='' " +
//            "or yearClass.classId = :yearClassId)"
//    )
//    List<EducationProgramSubjectDTO> findAllByYearClassId(@Param("yearClassId") String Id);
}
