package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.model.Employee;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;

import java.io.IOException;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})
public interface EmployeeService {
    Employee createEmployee(EmployeeDTO employeeDTO) throws IOException;

    Employee updateEmployee(EmployeeDTO employeeDTO) throws IOException;

    int deleteEmployee(EmployeeDTO employeeDTO) throws IOException;

    Page findBy(String employeeId, String departmentId, Integer type, Integer page, Integer pageSize) throws IOException;
}
