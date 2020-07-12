package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.model.Department;
import com.linkdoan.backend.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentService;
    @RequestMapping(value="/department/getAll",method = RequestMethod.GET)
    public ResponseEntity<?> getAll() throws Exception {
        List<Department> rs = departmentService.getAllDepartment();
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}
