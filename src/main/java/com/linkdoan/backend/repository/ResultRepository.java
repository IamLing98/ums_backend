package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.TermStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<TermStudent, Long> {

    Optional<TermStudent> findFirstByStudentIdAndTermId(String studentId, String termId);

    List<TermStudent> findAllByTermId(String termId);

}


