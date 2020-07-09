package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.ClassDTO;
import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.service.ClassService;
import com.linkdoan.backend.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @RequestMapping(value="/employee/findBy")
    public ResponseEntity<?> findBy(@Valid @ModelAttribute EmployeeDTO employeeDTO) throws Exception {
        Pageable pageable = PageRequest.of(employeeDTO.getPage(), employeeDTO.getPageSize());
        return new ResponseEntity<>(employeeService.findBy(pageable,employeeDTO), HttpStatus.OK);
    }
    @RequestMapping(value="/employee/create")
    public ResponseEntity<?> create(@Valid @ModelAttribute EmployeeDTO employeeDTO) throws Exception {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.OK);
    }
    @RequestMapping(value="/employee/update")
    public ResponseEntity<?> update(@Valid @ModelAttribute EmployeeDTO employeeDTO) throws Exception {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDTO), HttpStatus.OK);
    }
    @RequestMapping(value="/employee/delete")
    public ResponseEntity<?> delete(@Valid @ModelAttribute EmployeeDTO employeeDTO) throws Exception {
        return new ResponseEntity<>(employeeService.deleteEmployee(employeeDTO), HttpStatus.OK);
    }
}
