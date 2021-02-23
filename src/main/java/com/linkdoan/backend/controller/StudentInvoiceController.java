package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.StudentInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentInvoiceController {

    @Autowired
    StudentInvoiceService studentInvoiceService;

    @GetMapping(value = "studentInvoices")
    public ResponseEntity<?> getListStudentInvoices(@RequestParam("type") Integer type) {

        return new ResponseEntity<>(studentInvoiceService.getAll(type), HttpStatus.OK);
    }
}
