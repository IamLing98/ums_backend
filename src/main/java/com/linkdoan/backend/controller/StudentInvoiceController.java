package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.StudentInvoiceDTO;
import com.linkdoan.backend.service.StudentInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentInvoiceController {

    @Autowired
    StudentInvoiceService studentInvoiceService;

    @GetMapping(value = "/studentInvoices/{termId}")
    public ResponseEntity<?> getListStudentInvoices(@PathVariable("termId") String termId,
                                                    @RequestParam(name = "type", required = false) Integer type, SecurityContextHolder request) {
        return new ResponseEntity<>(studentInvoiceService.getAll(type, termId), HttpStatus.OK);
    }

    @PostMapping(value = "/studentInvoices")
    public ResponseEntity<?> createNewStudentInvoices(@RequestBody StudentInvoiceDTO studentInvoiceDTO,
                                                      SecurityContextHolder request) {
        String userId = request.getContext().getAuthentication().getName();
        return new ResponseEntity<>(studentInvoiceService.createNewStudentInvoice(studentInvoiceDTO,userId), HttpStatus.OK);
    }
}
