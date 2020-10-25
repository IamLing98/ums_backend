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

    public List<SubjectRegistrationDTO> getAll(String studentId) {
        Term currentTerm = termRepository.findFirstByStatus(1);
        if (currentTerm != null) {
            return subjectRegistrationRepository.getListSubjectRegistrationByStudentIdAndTermId(studentId, currentTerm.getId());
        }
        return Collections.emptyList();
    }

    public boolean addSubject(String studentId, SubjectRegistrationDTO subjectRegistrationDTO) {
        if (studentRepository.existsById(studentId) && subjectRepository.existsById(subjectRegistrationDTO.getSubjectId())) {
            Term currentTerm = termRepository.findFirstByStatus(1);
            if (currentTerm != null) {
                SubjectRegistration subjectRegistration = subjectRegistrationDTO.toSubjectRegistrationModel();
                subjectRegistration.setStudentId(studentId);
                subjectRegistration.setTermId(currentTerm.getId());
                if (subjectRegistrationRepository.findFirstByStudentIdAndDateAndSubjectIdAndTermId(studentId, subjectRegistrationDTO.getSubjectId(), currentTerm.getId()) != null) {
                    if (subjectRegistrationRepository.save(subjectRegistration) != null)
                        return true;
                }
            }
        }
        return false;
    }

    public int deleteSubject(String studentId, String subjectId) {
        if (studentRepository.existsById(studentId) && subjectId != ""  ) {
            Term currentTerm = termRepository.findFirstByStatus(1);
            if (currentTerm != null) {
                if (subjectRegistrationRepository.findFirstByStudentIdAndDateAndSubjectIdAndTermId(studentId, subjectId, currentTerm.getId()) != null) {
                    if (subjectRegistrationRepository.deleteByStudentIdAndSubjectIdAndTermId(studentId,subjectId, currentTerm.getId()) != 0) return 1;
                    else  throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Không tìm thấy");
                }
                else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Không tìm thấy");
            }
        }
        return 0;
    }
}
