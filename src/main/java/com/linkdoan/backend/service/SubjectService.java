package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.ClassDTO;
import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.Class;
import com.linkdoan.backend.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubjectService {
    public Page findBy(Pageable pageable , SubjectDTO subjectDTO);
    public Subject createSubject(SubjectDTO subjectDTO);
    public Subject updateSubject(SubjectDTO subjectDTO);
    public int deleteSubject(SubjectDTO subjectDTO);
}
