package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SubjectController {
    @Autowired
    SubjectServiceImpl subjectService;

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
        return new ResponseEntity<>(subjectService.getAll(page, pageSize, subjectId, subjectName, educationProgramId), HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @ModelAttribute SubjectDTO subjectDTO) throws Exception {
        return new ResponseEntity<>(subjectService.create(subjectDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/update", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @RequestBody SubjectDTO subjectDTO) throws Exception {
        return new ResponseEntity<>(subjectService.update(subjectDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/delete", method = RequestMethod.POST)
    public ResponseEntity<?> delete(@RequestBody List<SubjectDTO> subjectDTOList) throws Exception {
        if (subjectService.delete(subjectDTOList) == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
