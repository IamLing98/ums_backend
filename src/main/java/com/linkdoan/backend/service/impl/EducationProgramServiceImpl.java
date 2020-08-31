package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.EducationProgramDTO;
import com.linkdoan.backend.dto.EducationProgramSubjectDTO;
import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.Branch;
import com.linkdoan.backend.model.EducationProgram;
import com.linkdoan.backend.model.EducationProgramSubject;
import com.linkdoan.backend.model.Subject;
import com.linkdoan.backend.repository.BranchRepository;
import com.linkdoan.backend.repository.EducationProgramRepository;
import com.linkdoan.backend.repository.EducationProgramSubjectRepository;
import com.linkdoan.backend.repository.SubjectRepository;
import com.linkdoan.backend.service.EducationProgramService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EducationProgramServiceImpl implements EducationProgramService {

    @Autowired
    EducationProgramRepository educationProgramRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    EducationProgramSubjectRepository educationProgramSubjectRepository;

    @Qualifier("subjectRepository")
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<EducationProgramDTO> getAllProgram() {
        List<EducationProgramDTO> educationProgramDTOList = new ArrayList<>();
        List<EducationProgram> educationProgramList = educationProgramRepository.findAll();
        for (int i = 0; i < educationProgramList.size(); i++) {
            EducationProgram educationProgram = educationProgramList.get(i);
            EducationProgramDTO educationProgramDTO = educationProgram.toDTO();
            Branch branch = branchRepository.findFirstByBranchId(educationProgram.getBranchId());
            if (branch == null) throw new EntityNotFoundException("Not Found");
            educationProgramDTO.setBranchName(branch.getBranchName());
            educationProgramDTOList.add(educationProgramDTO);
        }
        return educationProgramDTOList;
    }

    @Override
    public EducationProgramDTO createNewEducationProgram(EducationProgramDTO educationProgramDTO) {
        EducationProgram educationProgram = educationProgramDTO.toModel();
        educationProgram.setEducationProgramStatus(2);
        return educationProgramRepository.save(educationProgram).toDTO();
    }

    @Override
    public EducationProgramDTO updateEducationProgram(EducationProgramDTO educationProgramDTO) {
        EducationProgram educationProgram = educationProgramDTO.toModel();
        educationProgram.setEducationProgramStatus(2);
        return educationProgramRepository.save(educationProgram).toDTO();
    }

    @Override
    public boolean delete(EducationProgramDTO educationProgramDTO) {
        if (educationProgramRepository.findById(educationProgramDTO.getEducationProgramId()).get() != null) {
            educationProgramRepository.delete(educationProgramDTO.toModel());
            return true;
        }
        return false;
    }

    @Override
    public List<EducationProgramSubject> updateEducationProgramSubject(List<EducationProgramSubjectDTO> educationProgramSubjectDTOList) {
        List<EducationProgramSubject> educationProgramSubjectList = new ArrayList<>();
        for(int i = 0 ; i< educationProgramSubjectDTOList.size();i++){
            EducationProgramSubject educationProgramSubject = educationProgramSubjectDTOList.get(i).toModel();
            educationProgramSubjectRepository.save(educationProgramSubject);
            educationProgramSubjectList.add(educationProgramSubject);
        }
        return educationProgramSubjectList;
    }

    @Override
    public EducationProgramDTO getDetails(EducationProgramDTO educationProgramDTO) {
        ExampleMatcher NAME_MATCHER = ExampleMatcher.matching()
                .withMatcher("educationProgramId", ExampleMatcher.GenericPropertyMatchers.ignoreCase());
        Example<EducationProgram> example = Example.<EducationProgram>of(educationProgramDTO.toModel(), NAME_MATCHER);
        boolean exists = educationProgramRepository.exists(example);
        if(exists == false) throw new EntityNotFoundException("Not found");
        EducationProgram educationProgram = educationProgramRepository.findFirstByEducationProgramId(educationProgramDTO.getEducationProgramId());
        EducationProgramDTO rs = educationProgram.toDTO();
        List<Subject> subjectList = subjectRepository.findAllByEducationProgramId(educationProgram.getEducationProgramId());
        List<SubjectDTO> subjectDTOList = new ArrayList<>();
        for(int i = 0 ; i < subjectList.size(); i++){
            SubjectDTO subjectDTO = subjectList.get(i).toDTO();
            subjectDTOList.add(subjectDTO);
        }
        rs.setSubjectList(subjectDTOList);
        return rs;

    }


}
