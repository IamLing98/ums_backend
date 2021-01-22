package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/subject/findBy", method = RequestMethod.POST)
    public ResponseEntity<?> findBy(@Valid @ModelAttribute SubjectDTO subjectDTO) throws Exception {
        Pageable pageable = PageRequest.of(subjectDTO.getPage(), subjectDTO.getPageSize());
        return new ResponseEntity<>(subjectService.findBy(pageable, subjectDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(name = "page", required = false) Integer page,
                                    @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                    @RequestParam(name = "subjectId", required = false) String subjectId,
                                    @RequestParam(name = "subjectName", required = false) String subjectName,
                                    @RequestParam(name = "educationProgramId", required = false) String educationProgramId
                                                                                    ) throws Exception {
        return new ResponseEntity<>(subjectService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.POST)
    public ResponseEntity<?> create( @RequestBody List<SubjectDTO> subjectDTOList) throws Exception {
        return new ResponseEntity<>(subjectService.create(subjectDTOList), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Param("id") String id, @RequestBody SubjectDTO subjectDTO) throws Exception {
        return new ResponseEntity<>(subjectService.update(  id,  subjectDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete( @RequestParam("ids") List<String> ids) throws Exception {
        return new ResponseEntity<>(subjectService.delete(ids), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects/importFile", method = RequestMethod.POST)
    public ResponseEntity<?> importFile(@RequestParam("file") MultipartFile file) throws Exception {
        return new ResponseEntity<>(subjectService.importFile(file), HttpStatus.OK);
    }
}
