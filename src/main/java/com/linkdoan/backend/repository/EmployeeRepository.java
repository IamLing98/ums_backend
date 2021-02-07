package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query(value =
            "SELECT new com.linkdoan.backend.dto.EmployeeDTO( employee.employeeId, employee.fullName, employee.dateBirth, employee.sex, employee.placeBorn,"
                    + "employee.contactAddress, employee.phoneNumber, employee.email, employee.degree, employee.degreeDetails,"
                    + "employee.scientificTitles, employee.scientificTitlesGetYear, employee.employeeType, employee.startWork,"
                    + "employee.avatar, employee.departmentId) "
                    + "FROM Employee  employee left join Department department on employee.departmentId = department.departmentId "
                    + "WHERE :employeeId is null  or :employeeId ='' or employee.employeeId = :employeeId "
                    + "and :departmentId is null or :departmentId= '' or employee.departmentId = :departmentId "
                    + "and :type is null or employee.employeeType = :type  "
    )
    List<EmployeeDTO> getAll(@Param("employeeId") String employeeId, @Param("departmentId") String departmentId, @Param("type") Integer type);

}
