package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department,String> {
    Department findFirstByDepartmentId(String departmentId);
 }
