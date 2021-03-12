package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {

    Optional<StudentSubject> findFirstByStudentIdAndEducationProgramId(String studentId, String educationProgramId);

}
