package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.StudentsFeeCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentFeeTuitionRepository extends JpaRepository<StudentsFeeCategories, Long> {

    Optional<StudentsFeeCategories> findFirstByStudentIdAndTermIdAndFeeCategoriesId(String studentId, String termId, Long feeCategoriesId);
}
