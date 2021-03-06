package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.EmployeeDTO;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})
public interface EmployeeService {

    EmployeeDTO getEmployeeDetail(String employeeId);

    List<EmployeeDTO> getAllEmployee(Long type);

    int create(EmployeeDTO employeeDTO);

    int updateEmployee(String employeeId, EmployeeDTO employeeDTO);

    int deleteEmployee(String employeeId);


}
