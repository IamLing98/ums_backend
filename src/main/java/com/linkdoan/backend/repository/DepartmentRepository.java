package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department,String> {
    Department findFirstByDepartmentId(String departmentId);

    @Query( value = "SELECT Department.*, Employee  FROM Department   WHERE (:department_id is null or :department_id =''  or department_id = :department_id)"
            + "ORDER BY department_id ASC ",
            countQuery = "SELECT count(*) FROM Department WHERE (:department_id is null or :department_id =''  or department_id = :department_id)",
            nativeQuery = true
    )
    List<Department> findBy(@Param("department_id") String departmentId
    );
 }
