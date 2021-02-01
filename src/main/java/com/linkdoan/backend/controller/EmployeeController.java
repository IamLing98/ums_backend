package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<?> findBy(@RequestParam(name = "employeeId", required = false) String employeeId,
                                    @RequestParam(name = "departmentId", required = false) String departmentId,
                                    @RequestParam(name = "type", required = false) Integer type) throws Exception {
        // Pageable pageable = PageRequest.of(employeeDTO.getPage(), employeeDTO.getPageSize());
        return new ResponseEntity<>(employeeService.findBy(employeeId, departmentId,  type), HttpStatus.OK);
    }

}
