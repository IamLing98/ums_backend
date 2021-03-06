package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.DocumentGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/documents")
public class DocumentGenerationController {

    @Autowired
    DocumentGenerationService documentGenerationService;

    @PostMapping("/pdf")
    public ResponseEntity generatePdfFile(@RequestBody HashMap<String, String> variables,
                                          @RequestParam(name = "id") Long id) {
        String result;
        try {
            result = documentGenerationService.generatePdfFile(variables, id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/excel")
    public ResponseEntity generateExcelFile(@RequestBody HashMap<String, Object> variables,
                                            @RequestParam(name = "id") Long id) {
        String result;
        try {
            result = documentGenerationService.generateExcelFile(variables, id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}