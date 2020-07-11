package com.linkdoan.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.linkdoan.backend.model.Class;
@Repository
public interface ClassRepository  extends JpaRepository<Class, String> {

    Class findClassByClassId(String classId);
}
