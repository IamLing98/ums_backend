package com.linkdoan.backend.controller;

import com.linkdoan.backend.base.dto.SearchDTO;
import com.linkdoan.backend.dto.StudentsDTO;
import com.linkdoan.backend.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class StudentsController {

    StudentService studentService;
    @PostMapping("/students/create")
    public ResponseEntity<?> createNewStudent(@RequestBody StudentsDTO obj) throws Exception {
        StudentsDTO crmLeadDTO = studentService.addStudent(obj);
//		List<String> listFile = crmLeadService.upLoadFileCrmLead(files, crmLeadDTO.getLeadId());
//		crmLeadDTO.setFiles(listFile);
        return new ResponseEntity(crmLeadDTO, HttpStatus.CREATED);
    }
    @PostMapping("/students/doSearch")
    public ResponseEntity doSearch(@ModelAttribute @Valid SearchDTO obj) {
        return new ResponseEntity<>( studentService.doSearch(obj), HttpStatus.OK);
    }
}
