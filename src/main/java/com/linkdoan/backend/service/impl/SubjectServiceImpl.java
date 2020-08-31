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
import java.util.ArrayList;
import java.util.List;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {
    @Qualifier("subjectRepository")
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Page findBy(Pageable pageable, SubjectDTO subjectDTO) {
        return null;
    }

    @Override
    public SubjectDTO create(SubjectDTO subjectDTO) {
        Subject subject = subjectDTO.toModel();
        return subjectRepository.save(subject).toDTO();
    }

    @Override
    public SubjectDTO update(SubjectDTO subjectDTO) {
        Subject subject = subjectDTO.toModel();
        return subjectRepository.save(subject).toDTO();
    }

    @Override
    public boolean delete(SubjectDTO subjectDTO) {
        Subject subject = subjectDTO.toModel();
        if(subjectRepository.findById(subjectDTO.getSubjectId()) != null){
            subjectRepository.delete(subject);
            return true;
        }
        return false;
    }

    @Override
    public List<SubjectDTO> getAll() {
        List<Subject> subjectList = subjectRepository.findAll();
        List<SubjectDTO> subjectDTOList = new ArrayList<>();
        for(int i = 0 ; i< subjectList.size(); i++){
            subjectDTOList.add(subjectList.get(i).toDTO());
        }
        return subjectDTOList;
    }

}
