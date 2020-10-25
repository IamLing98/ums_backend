package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.EducationProgramDTO;
import com.linkdoan.backend.dto.EducationProgramSubjectDTO;
import com.linkdoan.backend.model.EducationProgram;
import com.linkdoan.backend.model.EducationProgramSubject;
import com.linkdoan.backend.repository.BranchRepository;
import com.linkdoan.backend.repository.EducationProgramRepository;
import com.linkdoan.backend.repository.EducationProgramSubjectRepository;
import com.linkdoan.backend.repository.SubjectRepository;
import com.linkdoan.backend.service.EducationProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
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
    public List<EducationProgramDTO> getAllProgram(String branchId, String educationProgramId) {
        List<EducationProgramDTO> educationProgramDTOList = educationProgramRepository.findAll(branchId, educationProgramId);
        return educationProgramDTOList;
    }

    @Override
    public EducationProgramDTO createNewEducationProgram(EducationProgramDTO educationProgramDTO) {
        if (educationProgramRepository.findById(educationProgramDTO.getEducationProgramId()).get() != null)
            throw new EntityExistsException("Đã tồn tại");
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
        for (int i = 0; i < educationProgramDTOList.size(); i++) {
            EducationProgramDTO educationProgramDTO = educationProgramDTOList.get(i);
            if (educationProgramRepository.findById(educationProgramDTO.getEducationProgramId()).get() != null) {
                educationProgramRepository.delete(educationProgramDTO.toModel());

            } else throw new EntityNotFoundException("Khong tim thay");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<EducationProgramSubject> updateEducationProgramSubject(EducationProgramDTO educationProgramDTO) {
        educationProgramSubjectRepository.deleteAllByEducationProgramId(educationProgramDTO.getEducationProgramId());
        List<EducationProgramSubject> educationProgramSubjectList = new ArrayList<>();

//        for (int i = 0; i < educationProgramDTO.getSubjectList().size(); i++) {
//            EducationProgramSubject educationProgramSubject = educationProgramDTO.getSubjectList().get(i).toModel();
//            educationProgramSubjectRepository.save(educationProgramSubject);
//            educationProgramSubjectList.add(educationProgramSubject);
//        }
        return educationProgramSubjectList;
    }

    @Override
    public EducationProgramDTO getDetails(String educationProgramId) {
        if (educationProgramRepository.existsById(educationProgramId) == false)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        EducationProgramDTO educationProgramDTO = educationProgramRepository.findDTOById(educationProgramId);
        List<EducationProgramSubjectDTO> subjectDTOList = subjectRepository.findAllByEducationProgramId(educationProgramId);
        educationProgramDTO.setSubjectList(subjectDTOList);
        return educationProgramDTO;
    }


}
