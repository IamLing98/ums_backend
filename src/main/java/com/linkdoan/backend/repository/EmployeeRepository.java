package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByEmployeeId(String emplyeeId);

}
