package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.model.Department;
import com.linkdoan.backend.repository.DepartmentRepository;
import com.linkdoan.backend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Qualifier("departmentRepository")
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public List<Department> getAllDepartment() throws IOException {
        return departmentRepository.findAll();
    }
}
