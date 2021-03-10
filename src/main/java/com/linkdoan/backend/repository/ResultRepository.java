package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.TermStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<TermStudent, Long> {

}


