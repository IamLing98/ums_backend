package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.model.Employee;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.List;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})
public interface EmployeeService {
    Employee createEmployee(EmployeeDTO employeeDTO) throws IOException;

    Employee updateEmployee(EmployeeDTO employeeDTO) throws IOException;

    int deleteEmployee(EmployeeDTO employeeDTO) throws IOException;

    List<EmployeeDTO> findBy(String employeeId, String departmentId, Integer type) throws IOException;
}
