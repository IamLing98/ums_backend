package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.repository.EmployeeRepository;
import com.linkdoan.backend.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.io.IOException;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    private int checkExist(EmployeeDTO employeeDTO) {
        int result = 0;
        if (null != employeeDTO.getEmployeeId() && "" != employeeDTO.getEmployeeId()) {
            Employee employee = employeeRepository.findByEmployeeId(employeeDTO.getEmployeeId());
            if (null == employee) {
                result = 0;
            } else {
                result = 1;
            }
        }
        return result;
    }

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        if (checkExist(employeeDTO) == 1) throw new EntityExistsException("Nhân viên này đã tồn tại");
        return employeeRepository.save(employeeDTO.toModel());
    }

    @Override
    public Employee updateEmployee(EmployeeDTO employeeDTO) throws IOException {
        if (checkExist(employeeDTO) == 0) throw new EntityExistsException("Nhân viên này không tồn tại");
        return employeeRepository.save(employeeDTO.toModel());
    }

    @Override
    public int deleteEmployee(EmployeeDTO employeeDTO) {
        if (checkExist(employeeDTO) == 0) return 0;
        employeeRepository.delete(employeeDTO.toModel());
        return 1;
    }

    @Override
    public Page findBy(Pageable pageable, EmployeeDTO employeeDTO) {
        return null;
    }
}
