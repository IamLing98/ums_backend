package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.FeeCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeeCategoriesController {

    @Autowired
    FeeCategoriesService feeCategoriesService;

    @GetMapping(value="/feeCategories")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(feeCategoriesService.getAll(), HttpStatus.OK);
    }

}
