package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.ClassDTO;
import com.linkdoan.backend.dto.DepartmentDTO;
import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.dto.SpecializedDTO;
import com.linkdoan.backend.model.Department;
import com.linkdoan.backend.model.Specialized;
import com.linkdoan.backend.repository.SpecializedRepository;
import com.linkdoan.backend.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @RequestMapping(value = "/department/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(name = "departmentId", required = false) String departmentId) throws Exception {
        List<DepartmentDTO> rs = departmentService.getAllDepartment(departmentId);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @RequestMapping(value = "/department/getAllSpecialized", method = RequestMethod.POST)
    public ResponseEntity<?> getAllSpecialized(@Valid @ModelAttribute SpecializedDTO specializedDTO) throws Exception {
        return new ResponseEntity<>(departmentService.getAllSpecialized(specializedDTO), HttpStatus.OK);
    }
}
