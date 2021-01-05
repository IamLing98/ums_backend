package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.controller.SubjectRegistrationController;
import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.model.Subject;
import com.linkdoan.backend.model.SubjectRegistration;
import com.linkdoan.backend.model.Term;
import com.linkdoan.backend.repository.StudentRepository;
import com.linkdoan.backend.repository.SubjectRegistrationRepository;
import com.linkdoan.backend.repository.SubjectRepository;
import com.linkdoan.backend.repository.TermRepository;
import com.linkdoan.backend.service.StudentService;
import com.linkdoan.backend.service.SubjectRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@Service
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
        List<Object[]> result = subjectRegistrationRepository.getSubmittingInfo(termId);
        List<Map<String, Object>> rs = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            for (Object[] object : result) {
                detail = new HashMap<String, Object>();
                detail.put("subjectId", object[0]);
                detail.put("subjectName", object[1]);
                detail.put("totalSubmit", object[2]);
                rs.add(detail);
            }
        }
//        return subjectRegistrationRepository.getSubmittingInfo(termId);
        return rs;
    }

    //auto submit for new student
    public boolean subjectSubmitForNewStudent(String termId) {
        List<StudentDTO> studentDTOList = studentRepository.findAllStudentHasTermIsOne();
        List<SubjectDTO> subjectList = new ArrayList<>();
        LocalDate lt  = LocalDate.now();
        for(StudentDTO studentDTO : studentDTOList){
             subjectList = subjectRepository.findAllByEducationProgramIdAndTerm(studentDTO.getEducationProgramId(),studentDTO.getCurrentTerm() + 1);
             for(int i = 0 ; i < subjectList.size(); i++){
                 SubjectRegistrationDTO subjectRegistrationDTO = new SubjectRegistrationDTO(studentDTO.getStudentId(), subjectList.get(i).getSubjectId(), termId, lt );
                 subjectRegistrationRepository.save(subjectRegistrationDTO.toSubjectRegistrationModel());
             }
        }
        return true;
    }

    @Override
    public List<SubjectDTO> getListSubjectSubmitted(String termId, String studentId) {
        return subjectRegistrationRepository.getAllByStudentIdAndTermId(studentId, termId);
    }

    //student role
    @Override
    public boolean addSubject(String studentId, SubjectRegistrationDTO subjectRegistrationDTO) {
        if (studentRepository.existsById(studentId)) {
            Term nextTerm = termRepository.findFirstByStatus(2);
            if (nextTerm != null) {
                String termId = nextTerm.getId();
                String dtoTermId = subjectRegistrationDTO.getTermId();
                if (termId.equals(dtoTermId)) {
                    subjectRegistrationDTO.setStudentId(studentId);
                    subjectRegistrationDTO.setDate(LocalDate.now());
                    SubjectRegistration model = subjectRegistrationDTO.toSubjectRegistrationModel();
                    if (subjectRegistrationRepository.findFirstByStudentIdAndSubjectIdAndTermId(studentId, subjectRegistrationDTO.getSubjectId(), dtoTermId) == null) {
                        SubjectRegistration subjectRegistration1 = subjectRegistrationRepository.save(model);
                        return subjectRegistrationRepository.existsById(subjectRegistration1.getId());
                    } else throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã đăng ký!!!");
                } else throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã đăng ký!!!");
            } else throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã đăng ký!!!");
        } else throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã đăng ký!!!");
    }

    @Override
    public int deleteSubject(String studentId, String subjectId, String termId) {
        SubjectRegistration subjectRegistration = subjectRegistrationRepository.findFirstByStudentIdAndSubjectIdAndTermId(studentId, subjectId, termId);
        if (subjectRegistration != null) subjectRegistrationRepository.delete(subjectRegistration);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        return 0;
    }
}
