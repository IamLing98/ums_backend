package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SpecializedDTO;
import com.linkdoan.backend.model.Department;
import com.linkdoan.backend.model.Specialized;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface DepartmentService {
    List<Department> getAllDepartment(String departmentId)throws IOException;
    Page<Specialized> getAllSpecialized(SpecializedDTO specializedDTO) throws IOException;

}
