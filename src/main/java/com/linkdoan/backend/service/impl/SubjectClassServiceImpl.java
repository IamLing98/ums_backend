package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.model.SubjectClass;
import com.linkdoan.backend.model.SubjectClassSeq;
import com.linkdoan.backend.repository.SubjectClassRepository;
import com.linkdoan.backend.repository.SubjectClassSeqRepository;
import com.linkdoan.backend.service.SubjectClassRegistrationService;
import com.linkdoan.backend.service.SubjectClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
        (
                propagation = Propagation.REQUIRED,
                readOnly = false,
                rollbackFor = Throwable.class
        )
public class SubjectClassServiceImpl implements SubjectClassService {

    @Autowired
    SubjectClassRepository subjectClassRepository;

    @Autowired
    SubjectClassSeqRepository subjectClassSeqRepository;

    @Autowired
    SubjectClassRegistrationService subjectClassRegistrationService;

    @Override
    public List<Map<String, Object>> getAll(String termId) {
        String[] stringList = {"subjectId", "subjectName", "eachSubject", "departmentId",
                "theoryNumber", "selfLearningNumber", "exerciseNumber", "discussNumber",
                "practiceNumber", "subjectClassId", "isRequireLab", "teacherId", "duration",
                "numberOfSeats", "mainSubjectClassId", "dayOfWeek", "hourOfDay", "roomId",
                "fullName", "departmentName", "subjectType", "status", "currentOfSubmittingNumber"};
        List<Object[]> subjectClassObjectArrayList = subjectClassRepository.getSubjectClassObjectArraylist(termId);
        List<Map<String, Object>> rs = new ArrayList<>();
        if (subjectClassObjectArrayList != null && !subjectClassObjectArrayList.isEmpty()) {
            for (Object[] subjectClassObjectArray : subjectClassObjectArrayList) {
                Map<String, Object> subjectClassObjectMap = new HashMap<>();
                for (int i = 0; i < stringList.length; i++) {
                    subjectClassObjectMap.put(stringList[i], subjectClassObjectArray[i]);
                }
                rs.add(subjectClassObjectMap);
            }
        }
        return rs;
    }

    @Override
    public Map<String, Object> getDetail(String subjectClassId) {
        String[] stringList = {"subjectId", "subjectName", "eachSubject", "departmentId",
                "theoryNumber", "selfLearningNumber", "exerciseNumber", "discussNumber",
                "practiceNumber", "subjectClassId", "isRequireLab", "teacherId", "duration",
                "numberOfSeats", "mainSubjectClassId", "dayOfWeek", "hourOfDay", "roomId",
                "fullName", "departmentName", "subjectType", "status", "currentOfSubmittingNumber"};
        Map<String, Object> rs = new HashMap<>();
        List<Object[]> subjectClassObjectArrayList = subjectClassRepository.getFirstSubjectClassBySubjectClassId(subjectClassId);
        Object[] subjectClassObjectArray;
        if (subjectClassObjectArrayList != null && !subjectClassObjectArrayList.isEmpty()) {
            subjectClassObjectArray = subjectClassObjectArrayList.get(0);
            for (int i = 0; i < stringList.length; i++) {
                rs.put(stringList[i], subjectClassObjectArray[i]);
            }
            List<Object[]> studentObjectList = subjectClassRepository.getListStudentOfSubjectClass(subjectClassId);
            List<Map<String, Object>> studentMapStringObjectList = new ArrayList<>();
            String[] studentLabels = {"studentId", "fullName", "diemBaiTap", "diemChuyenCan", "diemKiemTra",
                    "diemThi", "diemThiLai", "diemTrungBinh", "diemThangBon", "departmentId", "departmentName",
                    "classId", "className", "dateBirth", "sex", "status", "rejectReason"};
            if (studentObjectList != null && !studentObjectList.isEmpty()) {
                for (Object[] subjectClassObject : studentObjectList) {
                    Map<String, Object> studentMap = new HashMap<>(2);
                    studentMap = new HashMap<String, Object>();
                    for (int i = 0; i < studentLabels.length; i++) {
                        studentMap.put(studentLabels[i], subjectClassObject[i]);
                    }
                    studentMapStringObjectList.add(studentMap);
                }
            }
            rs.put("studentList", studentMapStringObjectList);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy lớp học này");

        return rs;
    }


    @Override
    public List<SubjectClass> create(List<SubjectClassDTO> subjectClassDTOList) {
        LocalDate lt = LocalDate.now();
        List<SubjectClass> subjectClassList = new ArrayList<>();
        for (SubjectClassDTO subjectClassDTO : subjectClassDTOList) {
            Optional<SubjectClassSeq> subjectClassSeqOptional = subjectClassSeqRepository.findFirstByTermIdAndSubjectId(subjectClassDTO.getTermId(), subjectClassDTO.getSubjectId());
            SubjectClassSeq subjectClassSeq;
            if (subjectClassSeqOptional.isPresent()) {
                subjectClassSeq = subjectClassSeqOptional.get();
            } else {
                subjectClassSeq = new SubjectClassSeq();
                subjectClassSeq.setNextVal(1L);
                subjectClassSeq.setTermId(subjectClassDTO.getTermId());
                subjectClassSeq.setSubjectId(subjectClassDTO.getSubjectId());
                subjectClassSeqRepository.save(subjectClassSeq);
            }
            Long nextVal = subjectClassSeq.getNextVal();
            String nextId = subjectClassDTO.getTermId() + subjectClassDTO.getSubjectId() + nextVal;
            SubjectClass subjectClass = subjectClassDTO.toModel();
            subjectClass.setSubjectClassId(nextId);
            subjectClass.setCreatedDate(lt);
            subjectClass.setCurrentOfSubmittingNumber(0);
            subjectClassList.add(subjectClass);
            subjectClassSeq.setNextVal(subjectClassSeq.getNextVal() + 1);
            subjectClassSeqRepository.save(subjectClassSeq);
        }
        return subjectClassRepository.saveAll(subjectClassList);
    }

    @Override
    public int update(List<SubjectClassDTO> subjectClassDTOList, String actionType) {
        int count = 0;
        for (SubjectClassDTO subjectClassDTO : subjectClassDTOList) {
            Optional<SubjectClass> subjectClassOptional = subjectClassRepository.findById(subjectClassDTO.getSubjectClassId());
            if (subjectClassOptional.isPresent()) {
                SubjectClass subjectClass = subjectClassOptional.get();
                if (actionType.equals("OFF")) {
                    subjectClass.setStatus(0);
                    subjectClassRepository.save(subjectClass);
                    subjectClassRegistrationService.deleteAllSubmittingOfSubjectClass(subjectClass.getSubjectClassId(), subjectClass.getTermId(), actionType);
                } else {
                    subjectClassDTO.setTeacherId(subjectClassDTO.getTeacherId());
                    subjectClassDTO.setIsRequireLab(subjectClassDTO.getIsRequireLab());
                    subjectClassDTO.setNumberOfSeats(subjectClassDTO.getNumberOfSeats());
                    subjectClassRepository.save(subjectClass);
                }
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại lớp học phần");
            count++;
        }
        return count;
    }

    @Override
    public int delete(List<String> ids) {
        int count = 0;
        for (String id : ids) {
            Optional<SubjectClass> subjectClassOptional = subjectClassRepository.findById(id);
            if (subjectClassOptional.isPresent()) {
                SubjectClass subjectClass = subjectClassOptional.get();
                subjectClassRegistrationService.deleteAllSubmittingOfSubjectClass(subjectClass.getSubjectClassId(), subjectClass.getTermId(), "DELETE");
                subjectClassRepository.delete(subjectClass);
                count++;
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Khong ton tai");
        }
        return count;
    }
}
