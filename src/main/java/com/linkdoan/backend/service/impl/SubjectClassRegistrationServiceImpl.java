package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.SubjectClassRegistrationDTO;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.*;
import com.linkdoan.backend.service.SubjectClassRegistrationService;
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
    TermRepository termRepository;

    //admin role
    @Override
    public boolean subjectClassSubmitForNewStudent(String termId) {
        List<SubjectRegistration> subjectRegistrationList = subjectRegistrationRepository.findAllByTermIdAndAutoSubmit(termId, 1);
        LocalDate timeNow = LocalDate.now();
        for (SubjectRegistration subjectRegistration : subjectRegistrationList) {
            List<SubjectClass> subjectClassList = subjectClassRepository.findAllByTermIdAndSubjectId(termId, subjectRegistration.getSubjectId());
            boolean submitted = false;
            for (SubjectClass subjectClass : subjectClassList) {
                if (!submitted) {
                    if (subjectClass.getCurrentOfSubmittingNumber() < subjectClass.getNumberOfSeats()) {
                        SubjectClassRegistration subjectClassRegistration = new SubjectClassRegistration();
                        subjectClassRegistration.setSubmittedDate(timeNow);
                        subjectClassRegistration.setTermId(termId);
                        subjectClassRegistration.setSubjectClassId(subjectClass.getSubjectClassId());
                        subjectClassRegistration.setStudentId(subjectRegistration.getStudentId());
                        subjectClassRegistration.setAutoSubmit(1);
                        subjectClassRegistration.setSubjectId(subjectClass.getSubjectId());
                        subjectClassRegistration.setProgressSubmitted(new Long(21));
                        subjectClassRegistration.setStatus(1);
                        subjectClassRegistrationRepository.save(subjectClassRegistration);
                        subjectClass.setCurrentOfSubmittingNumber(subjectClass.getCurrentOfSubmittingNumber() + 1);
                        subjectClassRepository.save(subjectClass);
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
    public List<Map<String, Object>> getListSubmitted(String studentId, String termId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        //check student exist
        if (studentOptional.isPresent()) {
            Optional<Term> termOptional = termRepository.findById(termId);
            //check term exist
            if (termOptional.isPresent()) {
                List<Object[]> subjectClassListObjectArray = subjectClassRegistrationRepository.getListSubmittedByStudentIdAndTermId(studentId, termId);
                System.out.println("size of subjectClassListObjectArray: "+ subjectClassListObjectArray.size());
                List<Map<String, Object>> subjectClassListMap = new ArrayList<>();
                if(subjectClassListObjectArray!= null && !subjectClassListObjectArray.isEmpty()){
                    for(Object[] subjectClassListObject : subjectClassListObjectArray){
                        Map<String, Object> subjectClassListMapString = new HashMap<>();
                        subjectClassListMapString.put("scheduleId",subjectClassListObject[0]);
                        subjectClassListMapString.put("subjectClassId",subjectClassListObject[1]);
                        subjectClassListMapString.put("subjectId",subjectClassListObject[2]);
                        subjectClassListMapString.put("roomId",subjectClassListObject[3]);
                        subjectClassListMapString.put("hourOfDay",subjectClassListObject[4]);
                        subjectClassListMapString.put("dayOfWeek",subjectClassListObject[5]);
                        subjectClassListMapString.put("duration",subjectClassListObject[6]);
                        subjectClassListMapString.put("subjectName",subjectClassListObject[7]);
                        subjectClassListMapString.put("eachSubject",subjectClassListObject[8]);
                        subjectClassListMapString.put("progressSubmitted",subjectClassListObject[9]);
                        subjectClassListMapString.put("status",subjectClassListObject[10]);
                        subjectClassListMap.add(subjectClassListMapString);
                    }
                    return subjectClassListMap;
                }
                return subjectClassListMap;
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kỳ học không hợp lệ!!!");
        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Loi xac thuc");

    }

    @Override
    public SubjectClassRegistration submit(String studentId, SubjectClassRegistrationDTO subjectClassRegistrationDTO) {
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
//        //check student exist
//        if (studentOptional.isPresent()) {
//            Optional<Term> termOptional = termRepository.findById(subjectClassRegistrationDTO.getTermId());
//            //check term exist
//            if (termOptional.isPresent()) {
//                Term term = termOptional.get();
//                //check submittng is open
//                if (term.getProgress() != 21)
//                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Khonng hop le~~, chưa mở đăng ký lớp học phần");
//                Optional<SubjectClass> subjectClassOptional = subjectClassRepository.findById(subjectClassRegistrationDTO.getSubjectClassId());
//                if (subjectClassOptional.isPresent()) {
//                    SubjectClass subjectClass = subjectClassOptional.get();
//                    Optional<SubjectClassRegistration> subjectClassRegistrationOptional = subjectClassRegistrationRepository.findFirstBySubjectClassIdAndStudentId(subjectClassRegistrationDTO.getSubjectClassId(), studentId);
//                    if (subjectClassRegistrationOptional.isPresent()) {
//                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã đăng ký");
//                    } else {
//                        Optional<SubjectClassRegistration> subjectClassRegistrationList = subjectClassRegistrationRepository.findFirstBySubjectClassIdAndStudentId(subjectClassRegistrationDTO.getSubjectClassId(), studentId);
//                        if (!
//                                subjectClassRegistrationList.isPresent() )
//                            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Đã đăng ký một lớp học khác có học phần này có học phần này!!!");
//                        SubjectClassRegistration subjectClassRegistration = new SubjectClassRegistration();
//                        subjectClassRegistration.setAutoSubmit(0);
//                        subjectClassRegistration.setStudentId(studentId);
//                        subjectClassRegistration.setSubjectClassId(subjectClassRegistrationDTO.getSubjectClassId());
//                        subjectClassRegistration.setSubjectId(subjectClass.getSubjectId());
//                        subjectClassRegistration.setTermId(subjectClassRegistrationDTO.getTermId());
//                        subjectClassRegistration.setSubmittedDate(LocalDate.now());
//                        subjectClassRegistration.setProgressSubmitted(new Long(21));
//                        subjectClassRegistration.setStatus(1);
//                        Optional<ScheduleSubjectClass> scheduleSubjectClassOptional = scheduleSubjectClassRepository.findFirstByScheduleIdAndSubjectClassId(subjectClassRegistrationDTO.getScheduleId(), subjectClass.getSubjectClassId());
//                        if (scheduleSubjectClassOptional.isPresent()) {
//                            ScheduleSubjectClass scheduleSubjectClass = scheduleSubjectClassOptional.get();
//                            if (scheduleSubjectClass.getCurrentOfSubmittingNumber() >= scheduleSubjectClass.getMaxOfSubmittingNumber())
//                                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "lớp học phần đã đầy!!!");
//                            scheduleSubjectClass.setCurrentOfSubmittingNumber(scheduleSubjectClass.getCurrentOfSubmittingNumber() + 1);
//                            subjectClassRegistrationRepository.save(subjectClassRegistration);
//                            scheduleSubjectClassRepository.save(scheduleSubjectClass);
//                            return subjectClassRegistration;
//                        } else
//                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KHÔNG TÌM THẤYY LỚP HỌC PHẦN !!!");
//                    }
//                } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lop học phần không tồn tại!!!");
//            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Khoá học không hợp lệ!!!");
//        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Loi xac thuc");
        return null;
    }

    @Override
    public boolean delete(String studentId, String subjectClassId, Long scheduleId) {
//        System.out.println("Student id: " + studentId);
//        System.out.println("subjectClassId id: " + subjectClassId);
//        System.out.println("scheduleId id: " + scheduleId);
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
//        //check student exist
//        if (studentOptional.isPresent()) {
//            Optional<SubjectClassRegistration> subjectClassRegistrationOptional = subjectClassRegistrationRepository.findFirstBySubjectClassIdAndStudentId(subjectClassId, studentId);
//            if (subjectClassRegistrationOptional.isPresent()) {
//                SubjectClassRegistration subjectClassRegistration = subjectClassRegistrationOptional.get();
//                Optional<ScheduleSubjectClass> scheduleSubjectClassOptional = scheduleSubjectClassRepository.findFirstByScheduleIdAndSubjectClassId(scheduleId, subjectClassId);
//                if (scheduleSubjectClassOptional.isPresent()) {
//                    ScheduleSubjectClass scheduleSubjectClass = scheduleSubjectClassOptional.get();
//                    scheduleSubjectClass.setCurrentOfSubmittingNumber(scheduleSubjectClass.getCurrentOfSubmittingNumber() - 1);
//                    scheduleSubjectClassRepository.save(scheduleSubjectClass);
//                    subjectClassRegistrationRepository.delete(subjectClassRegistration);
//                    return true;
//                } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại");
//            }  //check SubjectClassRegistration exist
//            else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kỳ học không hợp lệ!!!");
//        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Loi xac thuc");
        return false;
    }

}
