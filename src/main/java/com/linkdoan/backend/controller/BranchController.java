package com.linkdoan.backend.controller;

import com.linkdoan.backend.model.Branch;
import com.linkdoan.backend.repository.RoleRepository;
import com.linkdoan.backend.service.impl.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BranchController {

    @Autowired
    BranchServiceImpl branchService ;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/branch/getAllBranch", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() throws Exception {
        List<Branch> rs = branchService.getAllBranch();
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


}