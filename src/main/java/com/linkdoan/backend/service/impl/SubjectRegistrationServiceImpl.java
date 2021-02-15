package com.linkdoan.backend.service.impl;

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
import com.linkdoan.backend.service.SubjectRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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
        LocalDateTime lt = LocalDateTime.now();
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
        Integer totalEachSubjectNumber = 0;
        List<Object[]> subjectSubmittedObjectArrayList = subjectRegistrationRepository.getAllByStudentIdAndTermId(studentId, subjectRegistrationDTO.getTermId());
        if (subjectSubmittedObjectArrayList != null && !subjectSubmittedObjectArrayList.isEmpty()) {
            for (Object[] subjecSubmittedObjectArray : subjectSubmittedObjectArrayList) {
                totalEachSubjectNumber += (Integer) subjecSubmittedObjectArray[7];
            }
        }
        if (studentRepository.existsById(studentId)) {
            Optional<Term> termOptional = termRepository.findById(subjectRegistrationDTO.getTermId());
            Optional<Subject> subjectOptional = subjectRepository.findById(subjectRegistrationDTO.getSubjectId());
            if (termOptional.isPresent() && subjectOptional.isPresent()) {
                Term term = termOptional.get();
                SubjectRegistration registrationOptional = subjectRegistrationRepository.findFirstByStudentIdAndSubjectIdAndTermId(studentId, subjectRegistrationDTO.getSubjectId(), subjectRegistrationDTO.getTermId());
                if (registrationOptional == null) {
                    LocalDateTime ldt = LocalDateTime.now();
                    System.out.println(ldt.toString());
                    boolean isBefore = ldt.isBefore(term.getSubjectSubmittingEndDate());
                    if (!isBefore)
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Đã kết thúc đăng ký học phần");
                    Subject sj = subjectOptional.get();
                    SubjectRegistration subjectRegistration = new SubjectRegistration();
                    subjectRegistration.setStudentId(studentId);
                    subjectRegistration.setTermId(subjectRegistrationDTO.getTermId());
                    subjectRegistration.setSubjectId(subjectRegistrationDTO.getSubjectId());
                    subjectRegistration.setAutoSubmit(0);
                    subjectRegistration.setDate(ldt);
                    if (totalEachSubjectNumber + sj.getEachSubject() < 26)
                        subjectRegistrationRepository.save(subjectRegistration);
                    else throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Vượt quá 25 tin chỉ một kỳ");
                    return true;
                } else throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã tồn tại");
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không hợp lệ");
        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sinh vien khong ton tai");
    }

    @Override
    public Map<String, Object> getListSubjectSubmitted(String termId, String studentId) {
        String[] labels = {"id", "termId", "studentId", "date", "autoSubmit", "subjectId", "subjectName", "eachSubject",};
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Map<String, Object> studentMap = new HashMap<>();
            Student student = studentOptional.get();
            studentMap.put("student", student);
            List<Object[]> subjectSubmittedObjectArrayList = subjectRegistrationRepository.getAllByStudentIdAndTermId(studentId, termId);
            List<Map<String, Object>> rs = new ArrayList<>();
            Integer numberSubjectSumitted = 0;
            if (subjectSubmittedObjectArrayList != null && !subjectSubmittedObjectArrayList.isEmpty()) {
                for (Object[] subjectSubmittedObjectArray : subjectSubmittedObjectArrayList) {
                    Map<String, Object> subjectSubmittedMap = new HashMap<>();
                    for (int i = 0; i < labels.length; i++) {
                        subjectSubmittedMap.put(labels[i], subjectSubmittedObjectArray[i]);
                    }
                    numberSubjectSumitted += (Integer) subjectSubmittedObjectArray[7];
                    rs.add(subjectSubmittedMap);
                }
            }
            studentMap.put("listSubjectSubmitted", rs);
            studentMap.put("totalSubmitted", numberSubjectSumitted);
            return studentMap;
        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Lỗi xác thực");
    }


    @Override
    public int deleteSubject(String studentId, String subjectId, String termId) {
        System.out.println("student: " + studentId);
        int deletSubjectSubmitted = subjectRegistrationRepository.deleteSubjectSubmitted(studentId, subjectId, termId);
        if (deletSubjectSubmitted > 0) {
            return 1;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
    }
}
