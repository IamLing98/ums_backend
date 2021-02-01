package com.linkdoan.backend.service;


import com.linkdoan.backend.model.Employee;

import java.util.List;

public interface TeacherService {
    List<Employee> getAll();

    Employee getDetail();
}
