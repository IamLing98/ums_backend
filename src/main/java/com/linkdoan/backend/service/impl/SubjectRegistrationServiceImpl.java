package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import com.linkdoan.backend.model.Subject;
import com.linkdoan.backend.model.SubjectRegistration;
import com.linkdoan.backend.model.Term;
import com.linkdoan.backend.repository.StudentRepository;
import com.linkdoan.backend.repository.SubjectRegistrationRepository;
import com.linkdoan.backend.repository.SubjectRepository;
import com.linkdoan.backend.repository.TermRepository;
import com.linkdoan.backend.service.SubjectRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class SubjectRegistrationServiceImpl implements SubjectRegistrationService {

    @Autowired
    SubjectRegistrationRepository subjectRegistrationRepository;

    @Autowired
    TermRepository termRepository;

    @Qualifier("studentRepository")
    @Autowired
    StudentRepository studentRepository;

    @Qualifier("subjectRepository")
    @Autowired
    SubjectRepository subjectRepository;

    //admin role

    @Override
    public List<Map<String, Object>> getSubmittingInfo(String termId) {
        Map<String, Object> detail = new HashMap<>(2);
        List<Object[]> predictSubjectSubmit = subjectRegistrationRepository.getPredictTotalSubmit(termId);
        List<Object[]> currentSubjectSubmit = subjectRegistrationRepository.getCurrentTotalSubmit(termId);
        List<Object[]> autoSubjectSubmit = subjectRegistrationRepository.getTotalAutoSubmit(termId);
        List<Object[]> totalSubjectClassOpened = subjectRegistrationRepository.getTotalClassWithSubject(termId);
        List<Map<String, Object>> rs = new ArrayList<>();
        if (predictSubjectSubmit != null && !predictSubjectSubmit.isEmpty()) {
            for (Object[] object : predictSubjectSubmit) {
                detail = new HashMap<String, Object>();
                detail.put("subjectId", object[0]);
                detail.put("subjectName", object[1]);
                detail.put("predictSubmit", object[2]);
                detail.put("discussNumber", object[3]);
                detail.put("exerciseNumber", object[4]);
                detail.put("practiceNumber", object[5]);
                detail.put("selfLearningNumber", object[6]);
                detail.put("theoryNumber", object[7]);
                detail.put("departmentName", object[8]);
                detail.put("subjectType", object[9]);
                detail.put("eachSubject", object[10]);
                if (currentSubjectSubmit != null && !currentSubjectSubmit.isEmpty()) {
                    Object[] hasPeopleSubmitted = currentSubjectSubmit.stream().filter(entry -> entry[0].toString().equals(object[0].toString())).findFirst().orElse(null);
                    if (hasPeopleSubmitted != null) {
                        detail.put("totalSubmit", hasPeopleSubmitted[2]);
                    } else {
                        detail.put("totalSubmit", 0);
                    }
                } else detail.put("totalSubmit", 0);
                if (autoSubjectSubmit != null && !autoSubjectSubmit.isEmpty()) {
                    Object[] hasAutoSubmitted = autoSubjectSubmit.stream().filter(entry -> entry[0].toString().equals(object[0].toString())).findFirst().orElse(null);
                    if (hasAutoSubmitted != null) {
                        detail.put("autoSubmit", hasAutoSubmitted[2]);
                    } else {
                        detail.put("autoSubmit", 0);
                    }
                } else detail.put("autoSubmit", 0);
                if (totalSubjectClassOpened != null && !totalSubjectClassOpened.isEmpty()) {
                    Object[] subjectClassObject = totalSubjectClassOpened.stream().filter(entry -> entry[0].toString().equals(object[0].toString())).findFirst().orElse(null);
                    if (subjectClassObject != null) {
                        detail.put("totalSubjectClassOpened", subjectClassObject[2]);
                    } else {
                        detail.put("totalSubjectClassOpened", 0);
                    }
                } else detail.put("totalSubjectClassOpened", 0);

                rs.add(detail);
            }
        }
//        return subjectRegistrationRepository.getSubmittingInfo(termId);
        return rs;
    }

    //auto submit for new student
    @Override
    public boolean subjectSubmitForNewStudent(String termId) {
        char termChar = termId.charAt(4);
        Integer termIndex = termChar - '0';
        System.out.println("termIndex: " + termIndex);
        List<StudentDTO> studentDTOList = studentRepository.findAllStudentHasTermEqualsTermIndex(termIndex);
        System.out.println("list student has term is one:" + studentDTOList.size());
        List<SubjectDTO> subjectList = new ArrayList<>();
        LocalDate lt = LocalDate.now();
        int studentListSize = studentDTOList.size();
        for (int j = 0; j < studentListSize; j++) {
            StudentDTO studentDTO = studentDTOList.get(j);
            subjectList = subjectRepository.findAllByEducationProgramIdAndTerm(studentDTO.getEducationProgramId(), studentDTO.getCurrentTerm() + 1);
            int subjectListSize = subjectList.size();
            for (int i = 0; i < subjectListSize; i++) {
                SubjectRegistrationDTO subjectRegistrationDTO = new SubjectRegistrationDTO(studentDTO.getStudentId(), subjectList.get(i).getSubjectId(), termId, lt, 1);
                subjectRegistrationRepository.save(subjectRegistrationDTO.toSubjectRegistrationModel());
            }
        }
        return true;
    }

    //student role
    @Override
    public boolean addSubject(String studentId, SubjectRegistrationDTO subjectRegistrationDTO) {
        System.out.println("student id: " + studentId);
        if (studentRepository.existsById(studentId)) {
            Optional<Term> term = termRepository.findById(subjectRegistrationDTO.getTermId());
            Optional<Subject> subjectOptional = subjectRepository.findById(subjectRegistrationDTO.getSubjectId());
            if (term.isPresent() && subjectOptional.isPresent()) {
                SubjectRegistration registrationOptional = subjectRegistrationRepository.findFirstByStudentIdAndSubjectIdAndTermId(studentId, subjectRegistrationDTO.getSubjectId(), subjectRegistrationDTO.getTermId());
                if (registrationOptional == null) {
                    SubjectRegistration subjectRegistration = new SubjectRegistration();
                    subjectRegistration.setStudentId(studentId);
                    subjectRegistration.setTermId(subjectRegistrationDTO.getTermId());
                    subjectRegistration.setSubjectId(subjectRegistrationDTO.getSubjectId());
                    subjectRegistration.setAutoSubmit(0);
                    subjectRegistration.setDate(LocalDate.now());
                    subjectRegistrationRepository.save(subjectRegistration);
                    return true;
                } else throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã tồn tại");
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không hợp lệ");
        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sinh vien khong ton tai");
    }

    @Override
    public List<SubjectDTO> getListSubjectSubmitted(String termId, String studentId) {
        return subjectRegistrationRepository.getAllByStudentIdAndTermId(studentId, termId);
    }


    @Override
    public int deleteSubject(String studentId, String subjectId, String termId) {
        SubjectRegistration subjectRegistration = subjectRegistrationRepository.findFirstByStudentIdAndSubjectIdAndTermId(studentId, subjectId, termId);
        if (subjectRegistration != null) subjectRegistrationRepository.delete(subjectRegistration);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        return 0;
    }
}
