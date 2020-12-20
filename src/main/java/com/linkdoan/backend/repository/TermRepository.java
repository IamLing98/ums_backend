package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TermRepository extends JpaRepository<Term, String> {

    Term findFirstByYearAndTerm(Long yearn, Long term);

    Term findFirstByStatus(Integer status);

    Optional<Term> findFirstById(String termId);
}
