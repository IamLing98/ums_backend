package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public ResponseEntity<?> findBy(@RequestParam(name = "employeeId", required = false) String employeeId,
                                    @RequestParam(name = "departmentId", required = false) String departmentId,
                                    @RequestParam(name = "page", required = false) Integer page,
                                    @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                    @RequestParam(name = "type", required = false) Integer type) throws Exception {
        // Pageable pageable = PageRequest.of(employeeDTO.getPage(), employeeDTO.getPageSize());
        return new ResponseEntity<>(employeeService.findBy(employeeId, departmentId, page, pageSize, type), HttpStatus.OK);
    }
    @RequestMapping(value="/teachers",method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody EmployeeDTO employeeDTO) throws Exception {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.OK);
    }
//    @RequestMapping(value="/employee/update",method = RequestMethod.POST)
//    public ResponseEntity<?> update(@Valid @ModelAttribute EmployeeDTO employeeDTO) throws Exception {
//        return new ResponseEntity<>(employeeService.updateEmployee(employeeDTO), HttpStatus.OK);
//    }
//    @RequestMapping(value="/employee/delete",method = RequestMethod.POST)
//    public ResponseEntity<?> delete(@Valid @ModelAttribute EmployeeDTO employeeDTO) throws Exception {
//        return new ResponseEntity<>(employeeService.deleteEmployee(employeeDTO), HttpStatus.OK);
//    }
}
