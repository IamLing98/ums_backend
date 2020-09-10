package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.Subject;
import com.linkdoan.backend.repository.EducationProgramSubjectRepository;
import com.linkdoan.backend.repository.SubjectRepository;
import com.linkdoan.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {
    @Qualifier("subjectRepository")
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private EducationProgramSubjectRepository educationProgramSubjectRepository;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(List<SubjectDTO> subjectDTOList) {
        for(int i = 0; i< subjectDTOList.size(); i++){
            Subject subject = subjectDTOList.get(i).toModel();
            if(subjectRepository.findById(subject.getSubjectId()) != null){
                subjectRepository.delete(subject);
                educationProgramSubjectRepository.deleteAllBySubjectId(subject.getSubjectId());
            }else throw new EntityNotFoundException("Not Found");
        }
        return true;
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
