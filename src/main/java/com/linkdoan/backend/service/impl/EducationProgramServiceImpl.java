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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public EducationProgramDTO getDetail(String educationProgramId) {
        if (!educationProgramRepository.findById(educationProgramId).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        EducationProgramDTO educationProgramDTO = educationProgramRepository.getDetail(educationProgramId);
        //getList Subject By EP
        List<Object[]> subjectObjectList = educationProgramRepository.getCorrectListSubjectByEp(educationProgramId);
        List<Map<String, Object>> subjectListMap = new ArrayList<>();
        if (!subjectObjectList.isEmpty()) {
            for (Object[] object : subjectObjectList) {
                Map<String, Object> subjectMap = new HashMap<>();
                subjectMap.put("departmentId", object[0]);
                subjectMap.put("discussNumber", object[1]);
                subjectMap.put("eachSubject", object[2]);
                subjectMap.put("exerciseNumber", object[3]);
                subjectMap.put("practiceNumber", object[4]);
                subjectMap.put("selfLearningNumber", object[5]);
                subjectMap.put("subjectForLevel", object[6]);
                subjectMap.put("subjectId", object[7]);
                subjectMap.put("subjectName", object[8]);
                subjectMap.put("theoryNumber", object[9]);
                subjectMap.put("departmentName", object[10]);
                subjectMap.put("term", object[11]);
                List<Object[]> preLearnSubjectList = subjectRepository.getPreviousLearnSubject(object[7] + "");
                if (preLearnSubjectList != null && !preLearnSubjectList.isEmpty()) {
                    List<Map<String, Object>> preLearnList = new ArrayList<>();
                    for (Object[] preObject : preLearnSubjectList) {
                        Map<String, Object> preObjectMap = new HashMap<>();
                        preObjectMap.put("subjectId", preObject[0]);
                        preObjectMap.put("subjectName", preObject[1]);
                        preLearnList.add(preObjectMap);
                    }
                    subjectMap.put("preLearnSubjectList", preLearnList);
                } else {
                    subjectMap.put("preLearnSubjectList", new ArrayList<>());
                }
                subjectListMap.add(subjectMap);
            }
            //set Subject List
            educationProgramDTO.setSubjectList(subjectListMap);
        }
        return educationProgramDTO;
    }

    @Override
    public List<EducationProgramDTO> getAllProgram(String branchId, String educationProgramId) {
        List<EducationProgramDTO> educationProgramDTOList = educationProgramRepository.findAll(branchId, educationProgramId);
        return educationProgramDTOList;
    }

    @Override
    public EducationProgramDTO create(EducationProgramDTO educationProgramDTO) {
        if (educationProgramRepository.findById(educationProgramDTO.getEducationProgramId()).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã tồn tại!!!");
        EducationProgram educationProgram = educationProgramDTO.toModel();
        educationProgram.setEducationProgramStatus(2);
        return educationProgramRepository.save(educationProgram).toDTO();
    }

    @Override
    public EducationProgramDTO update(String id, EducationProgramDTO educationProgramDTO) {
        if (!educationProgramRepository.findById(id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        EducationProgram educationProgram = educationProgramDTO.toModel();
        educationProgram.setEducationProgramStatus(2);
        return educationProgramRepository.save(educationProgram).toDTO();
    }

    @Override
    public boolean delete(String id) {
        if (!educationProgramRepository.findById(id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        educationProgramRepository.deleteById(id);
        return true;
    }

}
