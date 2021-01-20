package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.Subject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})

public interface SubjectService {
    Page findBy(Pageable pageable, SubjectDTO subjectDTO);

    List<Map<String, Object>> getAll();

    int create(List<SubjectDTO>  subjectDTO);

    Subject update(String id, SubjectDTO subjectDTO);

    int delete(List<String> subjectDTOList);

    String importFile(MultipartFile file);
}
