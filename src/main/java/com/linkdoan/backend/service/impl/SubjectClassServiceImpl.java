package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.model.SubjectClass;
import com.linkdoan.backend.repository.SubjectClassRepository;
import com.linkdoan.backend.service.SubjectClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@Service
public class SubjectClassServiceImpl implements SubjectClassService {

    @Autowired
    SubjectClassRepository subjectClassRepository;

    @Override
    public List<Map<String, Object>> getAll(String termId) {
        Map<String, Object> subjectClassMap = new HashMap<>(2);
        List<Object[]> subjectClassObjectsList = subjectClassRepository.getAllByTermIdd(termId);
        List<Map<String, Object>> rs = new ArrayList<>();
        if (subjectClassObjectsList != null && !subjectClassObjectsList.isEmpty()) {
            for (Object[] subjectClassObject : subjectClassObjectsList) {
                subjectClassMap = new HashMap<String, Object>();
                subjectClassMap.put("subjectClassId", subjectClassObject[0]);
                subjectClassMap.put("subjectId", subjectClassObject[1]);
                subjectClassMap.put("subjectName", subjectClassObject[2]);
                subjectClassMap.put("numberOfSeats", subjectClassObject[3]);
                subjectClassMap.put("isRequireLab", subjectClassObject[4]);
                subjectClassMap.put("employeeId", subjectClassObject[5]);
                subjectClassMap.put("employeeFullName", subjectClassObject[6]);
                subjectClassMap.put("duration", subjectClassObject[7]);
                rs.add(subjectClassMap);
            }
        }
        return rs;
    }

    @Override
    public Map<String, Object>  getDetail(String subjectClassId) {
        List<Object[]> studentObjectList = subjectClassRepository.getListStudent(subjectClassId);
        List<Map<String, Object>> rs = new ArrayList<>();
        Map<String, Object> newMap = new HashMap<>(2);
        if (studentObjectList != null && !studentObjectList.isEmpty()) {
            for (Object[] subjectClassObject : studentObjectList) {
                Map<String, Object> studentMap = new HashMap<>(2);
                studentMap = new HashMap<String, Object>();
                studentMap.put("studentId", subjectClassObject[0]);
                studentMap.put("fullName", subjectClassObject[1]);
                studentMap.put("diemBaiTap", subjectClassObject[2]);
                studentMap.put("diemChuyenCan", subjectClassObject[3]);
                studentMap.put("diemKiemTra", subjectClassObject[4]);
                studentMap.put("diemThi", subjectClassObject[5]);
                studentMap.put("diemThiLai", subjectClassObject[6]);
                studentMap.put("diemTrungBinh", subjectClassObject[7]);
                studentMap.put("diemThangBon", subjectClassObject[8]);
                rs.add(studentMap);
            }
            newMap.put("studentList", rs);
        }
        return newMap;
    }

    @Override
    public List<SubjectClass> create(List<SubjectClassDTO> subjectClassDTOList) {
        LocalDate lt = LocalDate.now();
        List<SubjectClass> subjectClassList = new ArrayList<>();
        for (SubjectClassDTO subjectClassDTO : subjectClassDTOList) {
            SubjectClass subjectClass = subjectClassDTO.toModel();
            subjectClass.setCreatedDate(lt);
            subjectClassList.add(subjectClass);
        }
        return subjectClassRepository.saveAll(subjectClassList);
    }

    @Override
    public int update(String subjectClassId, SubjectClassDTO subjectDTO) {
        Optional<SubjectClass> subjectClassOptional = subjectClassRepository.findById(subjectClassId);
        if (subjectClassOptional.isPresent()) {
            SubjectClass subjectClass = subjectClassOptional.get();
            subjectClass.setTeacherId(subjectDTO.getTeacherId());
            subjectClass.setIsRequireLab(subjectDTO.getIsRequireLab());
            subjectClass.setNumberOfSeats(subjectDTO.getNumberOfSeats());
            subjectClassRepository.save(subjectClass);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Khong ton tai");
        return 1;
    }

    @Override
    public boolean delete(String subjectClassDTOList) {
        Optional<SubjectClass> subjectClassOptional = subjectClassRepository.findById(subjectClassDTOList);
        if (subjectClassOptional.isPresent()) {
            SubjectClass subjectClass = subjectClassOptional.get();
            subjectClassRepository.delete(subjectClass);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Khong ton tai");
        return true;
    }
}
