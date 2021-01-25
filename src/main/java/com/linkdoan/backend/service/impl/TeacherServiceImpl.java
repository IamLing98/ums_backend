package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.repository.TeacherRepository;
import com.linkdoan.backend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Employee> getAll() {
        return teacherRepository.getAllByEmployeeType(1);
    }

    @Override
    public Employee getDetail() {
        return null;
    }
}
