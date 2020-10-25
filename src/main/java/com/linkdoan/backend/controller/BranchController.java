package com.linkdoan.backend.controller;

import com.linkdoan.backend.repository.RoleRepository;
import com.linkdoan.backend.service.impl.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchController {

    @Autowired
    BranchServiceImpl branchService ;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/branches", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(name = "departmentId", required = false) String departmentId) throws Exception {

        return new ResponseEntity<>( branchService.getAllBranch(departmentId) , HttpStatus.OK);
    }


}