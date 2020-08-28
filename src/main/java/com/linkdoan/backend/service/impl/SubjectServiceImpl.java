package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.Subject;
import com.linkdoan.backend.repository.SubjectRepository;
import com.linkdoan.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {
    @Qualifier("subjectRepository")
    @Autowired
    private SubjectRepository subjectRepository;
    private static final String SUBJECT = "Subject";

    private int checkExist(SubjectDTO subjectDTO) {
        int result = 0;
        if (null != subjectDTO.getSubjectId() && "" != subjectDTO.getSubjectId()) {
            Subject subject = subjectRepository.findBySubjectId(subjectDTO.getSubjectId());
            if (null == subject) {
                result = 0;
            } else {
                result = 1;
            }
        }
        return result;
    }

    @Override
    public Page findBy(Pageable pageable, SubjectDTO subjectDTO) {
        return subjectRepository.findAll(pageable);
    }

    @Override
    public Subject createSubject(SubjectDTO subjectDTO) {
        if (checkExist(subjectDTO) == 1) throw new EntityExistsException("Môn học này đã tồn tại");
        return subjectRepository.save(subjectDTO.toModel());
    }

    @Override
    public Subject updateSubject(SubjectDTO subjectDTO) {
        if (checkExist(subjectDTO) == 0) throw new EntityExistsException("Môn học này không tồn tại");
        return subjectRepository.save(subjectDTO.toModel());
    }

    @Override
    public int deleteSubject(SubjectDTO subjectDTO) {
        if (checkExist(subjectDTO) == 0) return 0;
        subjectRepository.delete(subjectDTO.toModel());
        return 1;
    }
}
