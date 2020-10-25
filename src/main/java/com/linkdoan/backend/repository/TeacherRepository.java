package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Employee, String> {

}
