package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.ResultDTO;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.*;
import com.linkdoan.backend.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Transactional(
        readOnly = false,
        rollbackFor = Throwable.class,
        propagation = Propagation.REQUIRED

)
public class ResultServiceImpl implements ResultService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectClassRegistrationRepository subjectClassRegistrationRepository;

    @Qualifier("subjectRepository")
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectClassRepository subjectClassRepository;

    @Autowired
    ResultRepository resultRepository;

    /**
     * Find rank of student in term
     *
     * @param GPA may be pojo or map parsed from field [1.5] 1 = xuat sac, 5 bang yeu
     * @return
     */
    private Integer getRank(Double GPA) {
        if (GPA >= 3.6) {
            return 1;

        } else if (GPA < 3.6 && GPA >= 3.2) {
            return 2;

        } else if (GPA < 3.2 && GPA >= 2.5) {
            return 3;

        } else if (GPA < 2.5 && GPA >= 2.0) {
            return 4;

        } else {
            return 5;
        }
    }

    @Override
    public boolean calculatorResult(String termId) {
        List<Student> studentList = studentRepository.findAll();
        List<TermStudent> termStudentList = new ArrayList<>();
        studentList.stream().forEach(student -> {
            String studentId = student.getStudentId();
            List<SubjectClassRegistration> subjectClassRegistrationList =
                    subjectClassRegistrationRepository.findAllByStudentIdAndTermIdAndStatus(studentId, termId, 1);
            if (subjectClassRegistrationList != null) {
                AtomicReference<Double> diemTrungBinh = new AtomicReference<Double>(0D);
                AtomicReference<Integer> totalCreditNumber = new AtomicReference<>(0);

                List<SubjectClassRegistration> newSubjectClassRegistration = subjectClassRegistrationList.stream()
                        .map(subjectClassRegistration -> {
                            Double diemBaiTap = subjectClassRegistration.getDiemBaiTap();
                            Double diemChuyenCan = subjectClassRegistration.getDiemChuyenCan();
                            Double diemKiemTra = subjectClassRegistration.getDiemKiemTra();
                            Double diemThi = subjectClassRegistration.getDiemThi();
                            Double perSubject = (diemBaiTap + diemChuyenCan + diemKiemTra) / 3 * 30 + diemThi * 70;
                            perSubject = perSubject / 100;
                            subjectClassRegistration.setDiemTrungBinh(perSubject);
                            return subjectClassRegistration;
                        }).collect(Collectors.toList());

                subjectClassRegistrationRepository.saveAll(newSubjectClassRegistration);

                subjectClassRegistrationList.stream().forEach(subjectClassRegistration -> {
                    Optional<SubjectClass> subjectClassOptional = subjectClassRepository.findById(subjectClassRegistration.getSubjectClassId());
                    if (!subjectClassOptional.isPresent())
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy class:" + subjectClassRegistration.getSubjectClassId());
                    SubjectClass subjectClass = subjectClassOptional.get();
                    Optional<Subject> subjectOptional = subjectRepository.findById(subjectClass.getSubjectId());
                    if (!subjectClassOptional.isPresent())
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy môn học:" + subjectClass.getSubjectId());
                    Subject subject = subjectOptional.get();
                    Integer creditNumber = subject.getEachSubject();
                    Double perSubjectGeade = subjectClassRegistration.getDiemTrungBinh();
                    totalCreditNumber.updateAndGet(v -> v + creditNumber);
                    diemTrungBinh.updateAndGet(v -> v + perSubjectGeade * creditNumber);
                });

                Double GPA = diemTrungBinh.get() / totalCreditNumber.get();

                Optional<TermStudent> termStudentOptional = resultRepository.findFirstByStudentIdAndTermId(studentId, termId);
                TermStudent termStudent = termStudentOptional.get();
                termStudent.setTermId(termId);
                termStudent.setGPA(GPA);
                termStudent.setStudentId(studentId);
                termStudent.setRank(getRank(GPA));
                termStudentList.add(termStudent);
            }
        });
        resultRepository.saveAll(termStudentList);
        return true;
    }

    @Override
    public List<ResultDTO> getResult(String termId, Integer rank) {
        List<TermStudent> termStudentList = resultRepository.findAllByTermId(termId);
        List<ResultDTO> resultDTOList = new ArrayList<>();
        if (termStudentList != null) {
            resultDTOList = termStudentList.stream().map(termStudent -> {
                ResultDTO resultDTO = termStudent.toDTO();
                Optional<Student> studentOptional = studentRepository.findById(termStudent.getStudentId());
                if (studentOptional.isPresent()) {
                    Student student = studentOptional.get();
                    resultDTO.setStudentId(student.getStudentId());
                    resultDTO.setStudent(student);
                }
                return resultDTO;
            }).collect(Collectors.toList());
        }
        return resultDTOList;
    }
}
