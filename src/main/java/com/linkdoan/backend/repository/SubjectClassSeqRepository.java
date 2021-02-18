package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.SubjectClassSeq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectClassSeqRepository extends JpaRepository<SubjectClassSeq, Long> {
    Optional<SubjectClassSeq> findFirstByTermIdAndSubjectId(String termId, String subjectId);
}
