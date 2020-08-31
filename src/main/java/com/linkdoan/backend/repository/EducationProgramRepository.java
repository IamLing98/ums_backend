package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.EducationProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationProgramRepository extends JpaRepository<EducationProgram, String> {

    List<EducationProgram> findAll();

   EducationProgram findFirstByEducationProgramId(String id);
}
