package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.Subject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})

public interface SubjectService {
    public Page findBy(Pageable pageable , SubjectDTO subjectDTO);
    public SubjectDTO create(SubjectDTO subjectDTO);
    public SubjectDTO update(SubjectDTO subjectDTO);
    public boolean delete(SubjectDTO subjectDTO);
    List<SubjectDTO> getAll();
}
