package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.GroupStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupStudent, Long> {

    int deleteByEducationProgramId(String educationProgramId);

}
