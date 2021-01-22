package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.EducationProgramSubjectDTO;
import com.linkdoan.backend.model.EducationProgramSubject;
import com.linkdoan.backend.repository.EducationProgramRepository;
import com.linkdoan.backend.repository.EducationProgramSubjectRepository;
import com.linkdoan.backend.repository.SubjectRepository;
import com.linkdoan.backend.service.EducationProgramSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class EducationProgramSubjectServiceImpl implements EducationProgramSubjectService {

    @Autowired
    EducationProgramSubjectRepository educationProgramSubjectRepository;

    @Autowired
    EducationProgramRepository educationProgramRepository;

    @Qualifier("subjectRepository")
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Map<String, Object>> getListSubjectCorrectWithEP(String id, boolean isIn) {
        if (!educationProgramRepository.findById(id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        if (isIn) {
            List<Object[]> subjectObjectList = educationProgramSubjectRepository.getCorrectListSubjectByEp(id);
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
                    subjectMap.put("id", object[12]);
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
            }
            return subjectListMap;
        } else {
            List<String> subjectIdList = subjectRepository.getListSubjectIdByEducationProgramId(id);
            List<Object[]> subjectObjectList;
            if (!subjectIdList.isEmpty()) {
                subjectObjectList = educationProgramSubjectRepository.getListSubjectNotInEducation(subjectIdList);
            } else {
                subjectObjectList = subjectRepository.getAll();
            }
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
            }
            return subjectListMap;
        }
    }

    @Override
    public int create(List<EducationProgramSubjectDTO> educationProgramSubjectDTOList) {
        int count = 0;
        for (EducationProgramSubjectDTO educationProgramSubjectDTO : educationProgramSubjectDTOList) {
            String subjectId = educationProgramSubjectDTO.getSubjectId();
            String educationProgramId = educationProgramSubjectDTO.getEducationProgramId();
            if (subjectRepository.findById(subjectId).isPresent() && educationProgramRepository.findById(educationProgramId).isPresent()) {
                if (!educationProgramSubjectRepository.findFirstBySubjectIdAndAndEducationProgramId(subjectId, educationProgramId).isPresent()) {
                    EducationProgramSubject educationProgramSubject = educationProgramSubjectDTO.toModel();
                    educationProgramSubjectRepository.save(educationProgramSubject);
                    count++;
                }
            } else {
                System.out.println("Đã ton tai");
            }
        }
        return count;
    }

    @Override
    public EducationProgramSubjectDTO change(String eduId, String sjId, EducationProgramSubjectDTO educationProgramSubjectDTO) {
        Optional<EducationProgramSubject> educationProgramSubjectOptional = educationProgramSubjectRepository.findFirstBySubjectIdAndAndEducationProgramId(sjId, eduId);
        if (educationProgramSubjectOptional.isPresent()) {
            EducationProgramSubject educationProgramSubject = educationProgramSubjectOptional.get();
            educationProgramSubject.setSubjectId(educationProgramSubjectDTO.getSubjectId());
            educationProgramSubject.setTerm(educationProgramSubjectDTO.getTerm());
            return educationProgramSubjectRepository.save(educationProgramSubject).toDTO();
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
    }

    @Override
    public int delete(String educationProgramId, List<Long> ids) {
        int count = 0;
        if (educationProgramRepository.findById(educationProgramId).isPresent()) {
            for (Long id : ids) {
                Optional<EducationProgramSubject> educationProgramSubjectOptional
                        = educationProgramSubjectRepository.findById(id);
                if (educationProgramSubjectOptional.isPresent()) {
                    EducationProgramSubject educationProgramSubject = educationProgramSubjectOptional.get();
                    if (educationProgramSubject.getEducationProgramId().equals(educationProgramId)) {
                        educationProgramSubjectRepository.delete(educationProgramSubject);
                        count++;
                    }
                }
            }
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chương trình đào tạo không tồn tại!!!");
        return count;
    }

}
