package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("subjectRepository")
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Subject findBySubjectId(String subjectId);
}
