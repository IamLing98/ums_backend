package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TermRepository extends JpaRepository<Term, String> {

    Term findFirstByYearAndTerm(Long yearn, Long term);

    Optional<Term> findFirstById(String termId);
}
