package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.*;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.*;
import com.linkdoan.backend.service.SubjectClassRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectClassRegistrationServiceImpl implements SubjectClassRegistrationService {

    @Autowired
    SubjectRegistrationRepository subjectRegistrationRepository;

    @Autowired
    SubjectClassRegistrationRepository subjectClassRegistrationRepository;

    @Qualifier("studentRepository")
    @Autowired
    StudentRepository studentRepository;

    @Qualifier("subjectRepository")
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectClassRepository subjectClassRepository;

    @Autowired
    ScheduleSubjectClassRepository scheduleSubjectClassRepository;

    @Autowired
    TermRepository termRepository;

    //admin role
    //    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean subjectClassSubmitForNewStudent(String termId, Long scheduleId) {
        List<SubjectRegistration> subjectRegistrationList = subjectRegistrationRepository.findAllByTermIdAndAutoSubmit(termId, 1);
        LocalDate timeNow = LocalDate.now();
        for (SubjectRegistration subjectRegistration : subjectRegistrationList) {
            List<ScheduleSubjectClass> scheduleSubjectClassList = scheduleSubjectClassRepository.findByScheduleIdAndSubjectId(scheduleId, subjectRegistration.getSubjectId());
            boolean submitted = false;
            for (ScheduleSubjectClass scheduleSubjectClass : scheduleSubjectClassList) {
                if (!submitted) {
                    if (scheduleSubjectClass.getCurrentOfSubmittingNumber() < scheduleSubjectClass.getMaxOfSubmittingNumber()) {
                        SubjectClassRegistration subjectClassRegistration = new SubjectClassRegistration();
                        subjectClassRegistration.setSubmittedDate(timeNow);
                        subjectClassRegistration.setTermId(termId);
                        subjectClassRegistration.setSubjectClassId(scheduleSubjectClass.getSubjectClassId());
                        subjectClassRegistration.setStudentId(subjectRegistration.getStudentId());
                        subjectClassRegistration.setAutoSubmit(1);
                        subjectClassRegistration.setSubjectId(scheduleSubjectClass.getSubjectId());
                        subjectClassRegistrationRepository.save(subjectClassRegistration);
                        scheduleSubjectClass.setCurrentOfSubmittingNumber(scheduleSubjectClass.getCurrentOfSubmittingNumber() + 1);
                        scheduleSubjectClassRepository.save(scheduleSubjectClass);
                        submitted = true;
                    }
                }
            }
            if (!submitted)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm được lớp phù hợp cho sinh viên: " + subjectRegistration.getStudentId() + "- " + subjectRegistration.getSubjectId());
        }
        return false;
    }

    /*
        Student Role
     */

    @Override
    public List<SubjectClassDTO> getListSubmitted(String studentId, String termId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        //check student exist
        if (studentOptional.isPresent()) {
            Optional<Term> termOptional = termRepository.findById(termId);
            //check term exist
            if (termOptional.isPresent()) {
                List<SubjectClassDTO> subjectClassList = subjectClassRegistrationRepository.getListSubmittedByStudentIdAndTermId(studentId, termId);
                return subjectClassList;
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kỳ học không hợp lệ!!!");
        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Loi xac thuc");

    }

    @Override
    public SubjectClassRegistration submit(String studentId, SubjectClassRegistrationDTO subjectClassRegistrationDTO) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        //check student exist
        if (studentOptional.isPresent()) {
            Optional<Term> termOptional = termRepository.findById(subjectClassRegistrationDTO.getTermId());
            //check term exist
            if (termOptional.isPresent()) {
                Term term = termOptional.get();
                //check submittng is open
                if (term.getProgress() != 21)
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Khonng hop le~~, chưa mở đăng ký lớp học phần");
                Optional<SubjectClass> subjectClassOptional = subjectClassRepository.findById(subjectClassRegistrationDTO.getSubjectClassId());
                if (subjectClassOptional.isPresent()) {
                    SubjectClass subjectClass = subjectClassOptional.get();
                    Optional<SubjectClassRegistration> subjectClassRegistrationOptional = subjectClassRegistrationRepository.findFirstBySubjectClassIdAndStudentId(subjectClassRegistrationDTO.getSubjectClassId(), studentId);
                    if (subjectClassRegistrationOptional.isPresent()) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã đăng ký");
                    } else {
                        List<SubjectClassRegistration> subjectClassRegistrationList = subjectClassRegistrationRepository.findAllByStudentIdAndSubjectIdAndTermId(studentId,subjectClass.getSubjectId(), subjectClassRegistrationDTO.getTermId() );
                        if(subjectClassRegistrationList.size() > 0 ) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Đã đăng ký lớp có học phần này!!!");
                        SubjectClassRegistration subjectClassRegistration = new SubjectClassRegistration();
                        subjectClassRegistration.setAutoSubmit(0);
                        subjectClassRegistration.setStudentId(studentId);
                        subjectClassRegistration.setSubjectClassId(subjectClassRegistrationDTO.getSubjectClassId());
                        subjectClassRegistration.setSubjectId(subjectClass.getSubjectId());
                        subjectClassRegistration.setTermId(subjectClassRegistrationDTO.getTermId());
                        subjectClassRegistration.setSubmittedDate(LocalDate.now());
                        Optional<ScheduleSubjectClass> scheduleSubjectClassOptional = scheduleSubjectClassRepository.findFirstByScheduleIdAndSubjectClassId(subjectClassRegistrationDTO.getScheduleId(), subjectClass.getSubjectClassId());
                        if(scheduleSubjectClassOptional.isPresent()){
                            ScheduleSubjectClass scheduleSubjectClass = scheduleSubjectClassOptional.get();
                            scheduleSubjectClass.setCurrentOfSubmittingNumber(scheduleSubjectClass.getCurrentOfSubmittingNumber() + 1);
                            subjectClassRegistrationRepository.save(subjectClassRegistration);
                            scheduleSubjectClassRepository.save(scheduleSubjectClass);
                            return subjectClassRegistration;
                        }else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"KHÔNG TÌM THẤYY LỚP HỌC PHẦN !!!");
                    }
                } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lop học phần không tồn tại!!!");
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Khoá học không hợp lệ!!!");
        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Loi xac thuc");
    }

}
