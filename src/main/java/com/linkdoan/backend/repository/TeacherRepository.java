package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Employee, String> {

    List<Employee> getAllByEmployeeTypeId(Long typeId);

}
