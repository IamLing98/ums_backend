package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.SubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectClassRepository extends JpaRepository<SubjectClass, String> {

}
