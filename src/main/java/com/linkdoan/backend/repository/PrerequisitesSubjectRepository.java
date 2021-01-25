package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.PrerequisitesSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrerequisitesSubjectRepository extends JpaRepository<PrerequisitesSubject, Long> {
    Optional<PrerequisitesSubject> findFirstBySubjectIdAndAndPrerequisitesSubjectId(String subjectId, String prerequisitesSubjectId);
    List<PrerequisitesSubject> findAllBySubjectId(String subjectId);
}
