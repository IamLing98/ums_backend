package com.linkdoan.backend.service;

import com.linkdoan.backend.model.Department;
import org.springframework.context.annotation.ComponentScan;
import java.io.IOException;
import java.util.List;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface DepartmentService {
    List<Department> getAllDepartment()throws IOException;

}
