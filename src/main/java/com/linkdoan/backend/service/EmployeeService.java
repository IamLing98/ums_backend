package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.model.Employee;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})
public interface EmployeeService {

    EmployeeDTO getEmployeeDetail(String employeeId);

    List<EmployeeDTO> getAllEmployee(Long type);

    int updateEmployee(EmployeeDTO employeeDTO);

    int deleteEmployee(String employeeId);


}
