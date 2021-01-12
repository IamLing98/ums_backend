package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.service.SubjectClassService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectClassServiceImpl implements SubjectClassService {

    @Override
    public List<SubjectClassDTO> getAll(String termId) {
        return null;
    }

    @Override
    public SubjectClassDTO getDetail(String subjectClassId) {
        return null;
    }

    @Override
    public SubjectDTO create(List<SubjectClassDTO> subjectDTO) {
        return null;
    }

    @Override
    public SubjectDTO update(String subjectClassId, SubjectClassDTO subjectDTO) {
        return null;
    }

    @Override
    public boolean delete(String subjectClassDTOList) {
        return false;
    }
}
