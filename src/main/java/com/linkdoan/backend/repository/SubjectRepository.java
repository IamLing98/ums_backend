package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subjectRepository")
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Subject findBySubjectId(String subjectId);

    @Query(value = "SELECT s  FROM Subject  s, EducationProgramSubject es  WHERE (s.subjectId = es.subjectId AND es.educationProgramId = :edId )"
    )
    List<Subject> findAllByEducationProgramId(@Param("edId") String Id);

    Subject findFirstBySubjectId(String subjectId);
}
