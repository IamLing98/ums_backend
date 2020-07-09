package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface EmployeeService {
     Employee createEmployee(EmployeeDTO employeeDTO);
    Employee updateEmployee(EmployeeDTO employeeDTO) throws IOException;
    int deleteEmployee(EmployeeDTO employeeDTO);
    Page findBy(org.springframework.data.domain.Pageable pageable, EmployeeDTO employeeDTO);
}
