package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentDTO;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Object[]> predictSubjectSubmit = subjectRegistrationRepository.getPredictTotalSubmit(termId);
        List<Object[]> currentSubjectSubmit = subjectRegistrationRepository.getCurrentTotalSubmit(termId);
        List<Object[]> autoSubjectSubmit = subjectRegistrationRepository.getTotalAutoSubmit(termId);
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

                if(currentSubjectSubmit != null && !currentSubjectSubmit.isEmpty()){
                    Object[] hasPeopleSubmitted = currentSubjectSubmit.stream().filter(entry -> entry[0].toString().equals(object[0].toString())).findFirst().orElse(null);
                    if(hasPeopleSubmitted != null){
                        detail.put("totalSubmit", hasPeopleSubmitted[2]);
                    }
                    else{
                        detail.put("totalSubmit", 0);
                    }
                }else  detail.put("totalSubmit", 0);
                if(autoSubjectSubmit != null && !autoSubjectSubmit.isEmpty()){
                    Object[] hasAutoSubmitted = autoSubjectSubmit.stream().filter(entry -> entry[0].toString().equals(object[0].toString())).findFirst().orElse(null);
                    if(hasAutoSubmitted != null){
                        detail.put("autoSubmit", hasAutoSubmitted[2]);
                    }
                    else{
                        detail.put("autoSubmit", 0);
                    }
                }else detail.put("autoSubmit", 0);
                rs.add(detail);
            }
        }
//        return subjectRegistrationRepository.getSubmittingInfo(termId);
        return rs;
    }

    //auto submit for new student
    @Override
    public boolean subjectSubmitForNewStudent(String termId) {
        List<StudentDTO> studentDTOList = studentRepository.findAllStudentHasTermIsOne();
        System.out.println("list student has term is one:" + studentDTOList.size());
        List<SubjectDTO> subjectList = new ArrayList<>();
        LocalDate lt  = LocalDate.now();
        int studentListSize = studentDTOList.size();
        for(int j = 0 ; j < studentListSize; j++){
            StudentDTO studentDTO = studentDTOList.get(j);
             subjectList = subjectRepository.findAllByEducationProgramIdAndTerm(studentDTO.getEducationProgramId(),studentDTO.getCurrentTerm() + 1);
             int subjectListSize = subjectList.size();
             for(int i = 0 ; i < subjectListSize; i++){
                 SubjectRegistrationDTO subjectRegistrationDTO = new SubjectRegistrationDTO(studentDTO.getStudentId(), subjectList.get(i).getSubjectId(), termId, lt, 1  );
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
