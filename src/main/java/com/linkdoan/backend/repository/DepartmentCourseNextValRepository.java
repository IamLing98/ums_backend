package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.primaryKey.DepartmentCourseNextVal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentCourseNextValRepository extends JpaRepository<DepartmentCourseNextVal, Integer> {
    DepartmentCourseNextVal findDistinctFirstByDepartmentIdAndAndCourseNumber(String departmentId, Integer courseNumber);
}
