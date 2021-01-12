package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})

public interface SubjectService {
    Page findBy(Pageable pageable, SubjectDTO subjectDTO);

    SubjectDTO create(SubjectDTO subjectDTO);

    SubjectDTO update(SubjectDTO subjectDTO);

    boolean delete(List<SubjectDTO> subjectDTOList);

    List<SubjectDTO> getAll(Integer page, Integer pageSize, String subjectId, String subjectName, String educationProgramId);
}
