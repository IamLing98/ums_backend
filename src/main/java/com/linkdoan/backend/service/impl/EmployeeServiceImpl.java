package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.repository.EmployeeRepository;
import com.linkdoan.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO getEmployeeDetail(String employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        List<EmployeeDTO> employeeDTOList = employeeRepository.getDetail(employeeId);
        if (employeeDTOList != null && !employeeDTOList.isEmpty()) {
            EmployeeDTO rs = employeeDTOList.get(0);
            if (rs != null) {
                rs.setTeacherEducationTimeLineList(employeeRepository.getListEducationTimeline(rs.getEmployeeId()));
                rs.setTeacherWorkTimeLineList(employeeRepository.getListWorkTimeline(rs.getEmployeeId()));
                rs.setSubjectList(employeeRepository.getListSubject(rs.getEmployeeId()));
            }
            return rs;
        } else return null;
    }

    @Override
    public List<EmployeeDTO> getAllEmployee(Long type) {
        List<EmployeeDTO> rs = new ArrayList<>();
        if (type != null && type == 1L) {
            rs = employeeRepository.findAllTeacher();
        } else {
            rs = employeeRepository.findAllEmployee();
        }

        return rs;
    }

    @Override
    public int updateEmployee(EmployeeDTO employeeDTO) {
        return 0;
    }

    @Override
    public int deleteEmployee(String employeeId) {
        return 0;
    }
}
