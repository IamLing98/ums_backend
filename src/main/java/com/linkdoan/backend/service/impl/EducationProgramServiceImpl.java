package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.EducationProgramDTO;
import com.linkdoan.backend.dto.EducationProgramSubjectDTO;
import com.linkdoan.backend.model.Branch;
import com.linkdoan.backend.model.EducationProgram;
import com.linkdoan.backend.model.EducationProgramSubject;
import com.linkdoan.backend.model.Subject;
import com.linkdoan.backend.repository.BranchRepository;
import com.linkdoan.backend.repository.EducationProgramRepository;
import com.linkdoan.backend.repository.EducationProgramSubjectRepository;
import com.linkdoan.backend.repository.SubjectRepository;
import com.linkdoan.backend.service.EducationProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            Optional<Branch> branch = branchRepository.findFirstByBranchId(educationProgram.getBranchId());
            if (branch.isPresent() == false) throw new EntityNotFoundException("Not Found");
            educationProgramDTO.setBranchName(branch.get().getBranchName());
            educationProgramDTOList.add(educationProgramDTO);
        }
        return educationProgramDTOList;
    }

    @Override
    public EducationProgramDTO createNewEducationProgram(EducationProgramDTO educationProgramDTO) {
        if (educationProgramRepository.findById(educationProgramDTO.getEducationProgramId()).get() != null) throw  new EntityExistsException("Đã tồn tại");
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
    public boolean delete(List<EducationProgramDTO> educationProgramDTOList) {
            for(int i = 0 ; i < educationProgramDTOList.size(); i++){
                EducationProgramDTO educationProgramDTO = educationProgramDTOList.get(i);
                if (educationProgramRepository.findById(educationProgramDTO.getEducationProgramId()).get() != null) {
                    educationProgramRepository.delete(educationProgramDTO.toModel());

                }else throw new EntityNotFoundException("Khong tim thay");
            }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<EducationProgramSubject> updateEducationProgramSubject(EducationProgramDTO educationProgramDTO) {
        educationProgramSubjectRepository.deleteAllByEducationProgramId(educationProgramDTO.getEducationProgramId());
        List<EducationProgramSubject> educationProgramSubjectList = new ArrayList<>();

        for (int i = 0; i < educationProgramDTO.getSubjectList().size(); i++) {
            EducationProgramSubjectDTO educationProgramSubjectDTO = educationProgramDTO.getSubjectList().get(i);
            EducationProgramSubject educationProgramSubject = educationProgramSubjectDTO.toModel();
            educationProgramSubjectRepository.save(educationProgramSubject);
            educationProgramSubjectList.add(educationProgramSubject);
        }
        return educationProgramSubjectList;
    }

    @Override
    public EducationProgramDTO getDetails(EducationProgramDTO educationProgramDTO) {
        ExampleMatcher NAME_MATCHER = ExampleMatcher.matching()
                .withMatcher("educationProgramId", ExampleMatcher.GenericPropertyMatchers.ignoreCase());
        Example<EducationProgram> example = Example.<EducationProgram>of(educationProgramDTO.toModel(), NAME_MATCHER);//create new education model
        boolean exists = educationProgramRepository.exists(example);
        if (exists == false) throw new EntityNotFoundException("Not found");

        EducationProgram educationProgram = educationProgramRepository.findFirstByEducationProgramId(educationProgramDTO.getEducationProgramId());
        EducationProgramDTO rs = educationProgram.toDTO();
        List<EducationProgramSubject> educationProgramSubjectList = educationProgramSubjectRepository.findAllByEducationProgramId(educationProgramDTO.getEducationProgramId());
        List<EducationProgramSubjectDTO> educationProgramSubjectDTOList = new ArrayList<>();
        for (int i = 0; i < educationProgramSubjectList.size(); i++) {
            EducationProgramSubject educationProgramSubject = educationProgramSubjectList.get(i);
            EducationProgramSubjectDTO educationProgramSubjectDTO = educationProgramSubject.toDTO();
            Subject subject = subjectRepository.findFirstBySubjectId(educationProgramSubject.getSubjectId());
            if (subject != null) educationProgramSubjectDTO.setSubject(subject.toDTO());
            else educationProgramSubjectDTO.setSubject(null);
            educationProgramSubjectDTOList.add(educationProgramSubjectDTO);
        }
        rs.setSubjectList(educationProgramSubjectDTOList);
        return rs;

    }


}
