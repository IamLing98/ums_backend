package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.repository.EmployeeRepository;
import com.linkdoan.backend.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.io.IOException;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) throws IOException {
        if(employeeRepository.existsById(employeeDTO.getEmployeeId()) == true) throw  new EntityExistsException("Exist");
        return employeeRepository.save(employeeDTO.toModel());
    }

    @Override
    public Employee updateEmployee(EmployeeDTO employeeDTO) throws IOException {
        return null;
    }

    @Override
    public int deleteEmployee(EmployeeDTO employeeDTO) throws IOException {
        return 0;
    }

    @Override
    public Page<EmployeeDTO> findBy(String employeeId, String departmentId, Integer type, Integer page, Integer pageSize) {
        logger.info("start");
        if (page == null) page = 0;
        if (pageSize == null) pageSize = 999999;
        Pageable pageable = PageRequest.of(page, pageSize);
        return employeeRepository.findBy(employeeId, departmentId, type, pageable);
    }
}
