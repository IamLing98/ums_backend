package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {

    Term findFirstByYearAndTerm(Long yearn, Long term);

    Term findFirstByStatus(Integer status);
}
