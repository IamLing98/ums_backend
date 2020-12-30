package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.controller.SubjectRegistrationController;
import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.dto.SubjectRegistrationDTO;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

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

    public List<SubjectRegistration> getAll(String studentId) {
        Term currentTerm = termRepository.findFirstByStatus(1);
        if (currentTerm != null) {
            return subjectRegistrationRepository.getAllByStudentIdAndTermId(studentId, currentTerm.getId());
        }
        return Collections.emptyList();
    }

    @Override
    public boolean addSubject(String studentId, SubjectRegistrationDTO subjectRegistrationDTO) {
        if (studentId.equals(subjectRegistrationDTO.getStudentId())) {
            Term currentTerm = termRepository.findFirstByStatus(1);
            if ((currentTerm.getId().equals(subjectRegistrationDTO.getTermId()))) {
                SubjectRegistration subjectRegistration = subjectRegistrationDTO.toSubjectRegistrationModel();
                if (subjectRegistrationRepository.findFirstByStudentIdAndSubjectIdAndTermId(studentId, subjectRegistrationDTO.getSubjectId(), currentTerm.getId()) != null) {
                    if (subjectRegistrationRepository.save(subjectRegistration) != null)
                        return true;
                }else throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã đăng ký!!!");
            }else throw new ResponseStatusException(HttpStatus.CONFLICT, "Không thể thực hiện!!!");
        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Có lỗi xảy ra!!!");
        return false;
    }

    @Override
    public int deleteSubject(String studentId, String subjectId, String termId) {
        SubjectRegistration subjectRegistration = subjectRegistrationRepository.findFirstByStudentIdAndSubjectIdAndTermId(studentId,subjectId,termId);
        if(subjectRegistration != null) subjectRegistrationRepository.delete(subjectRegistration);
        else throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        return 0;
    }
}
