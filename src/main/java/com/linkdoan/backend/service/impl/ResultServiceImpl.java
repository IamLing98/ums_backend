package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.ResultDTO;
import com.linkdoan.backend.dto.StudentResult;
import com.linkdoan.backend.dto.SubjectClassRegistrationDTO;
import com.linkdoan.backend.dto.TermResult;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.*;
import com.linkdoan.backend.service.EducationProgramService;
import com.linkdoan.backend.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
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

    @Autowired
    YearClassRepository yearClassRepository;

    @Autowired
    EducationProgramRepository educationProgramRepository;

    @Autowired
    TermRepository termRepository;

    @Autowired
    EducationProgramService educationProgramService;

    @Autowired
    StudentSubjectRepository studentSubjectRepository;

    /**
     * Find rank of student in term
     *
     * @param GPA may be pojo or map parsed from field [1.5] 1 = xuat sac, 5 bang yeu
     * @return
     */

    private Map<String, Object> convertDiemThangMuoiToDiemThangBon(Double diemThangMuoi) {
        Map<String, Object> stringObjectMap = new HashMap<>();
        if (diemThangMuoi >= 8.5) {
            stringObjectMap.put("diemThangBon", 4.0);
            stringObjectMap.put("diemChu", "A");
        } else if (diemThangMuoi >= 8.0 && diemThangMuoi < 8.5) {
            stringObjectMap.put("diemThangBon", 3.5);
            stringObjectMap.put("diemChu", "B+");
        } else if (diemThangMuoi >= 7.0 && diemThangMuoi < 8.0) {
            stringObjectMap.put("diemThangBon", 3.2);
            stringObjectMap.put("diemChu", "B");
        } else if (diemThangMuoi >= 6.5 && diemThangMuoi < 7.0) {
            stringObjectMap.put("diemThangBon", 2.5);
            stringObjectMap.put("diemChu", "C+");
        } else if (diemThangMuoi >= 5.5 && diemThangMuoi < 6.4) {
            stringObjectMap.put("diemThangBon", 2.0);
            stringObjectMap.put("diemChu", "C");
        } else if (diemThangMuoi >= 5.0 && diemThangMuoi < 5.5) {
            stringObjectMap.put("diemThangBon", 1.5);
            stringObjectMap.put("diemChu", "D+");
        } else if (diemThangMuoi >= 4.0 && diemThangMuoi < 4.9) {
            stringObjectMap.put("diemThangBon", 1.0);
            stringObjectMap.put("diemChu", "D");
        } else {
            stringObjectMap.put("diemThangBon", 0.0);
            stringObjectMap.put("diemChu", "F");
        }
        return stringObjectMap;
    }

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
            String educationProgramId = student.getEducationProgramId();
            List<SubjectClassRegistration> subjectClassRegistrationList =
                    subjectClassRegistrationRepository.findAllByStudentIdAndTermIdAndStatus(studentId, termId, 1);
            if (subjectClassRegistrationList != null) {
                AtomicReference<Double> diemTrungBinh = new AtomicReference<Double>(0D);

                AtomicReference<Integer> totalCreditNumber = new AtomicReference<>(0);

                List<StudentSubject> studentSubjectList = new ArrayList<>();

                List<SubjectClassRegistration> newSubjectClassRegistration = subjectClassRegistrationList.stream()
                        .map(subjectClassRegistration -> {
                            Double diemBaiTap = subjectClassRegistration.getDiemBaiTap();
                            Double diemChuyenCan = subjectClassRegistration.getDiemChuyenCan();
                            Double diemKiemTra = subjectClassRegistration.getDiemKiemTra();
                            Double diemThi = subjectClassRegistration.getDiemThi();
                            Double perSubject = (diemBaiTap + diemChuyenCan + diemKiemTra) / 3 * 30 + diemThi * 70;
                            perSubject = perSubject / 100;
                            subjectClassRegistration.setDiemTrungBinh(perSubject);
                            Map<String, Object> stringObjectMap = convertDiemThangMuoiToDiemThangBon(perSubject);
                            Double diemThangBon = (Double) stringObjectMap.get("diemThangBon");
                            String diemChu = (String) stringObjectMap.get("diemChu");
                            subjectClassRegistration.setDiemThangBon(diemThangBon);
                            subjectClassRegistration.setDiemChu(diemChu);
                            Optional<StudentSubject> studentSubjectOptional = studentSubjectRepository.findFirstByStudentIdAndEducationProgramId(studentId, educationProgramId);
                            if (studentSubjectOptional.isPresent()) {
                                StudentSubject studentSubject = studentSubjectOptional.get();
                                studentSubject.setDiemTrungBinh(perSubject);
                                studentSubject.setDiemThangBon(diemThangBon);
                                studentSubject.setDiemChu(diemChu);
                                studentSubjectList.add(studentSubject);
                            }
                            return subjectClassRegistration;
                        }).collect(Collectors.toList());

                subjectClassRegistrationRepository.saveAll(newSubjectClassRegistration);

                studentSubjectRepository.saveAll(studentSubjectList);

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
                    Double diemThangBon = subjectClassRegistration.getDiemThangBon();
                    totalCreditNumber.updateAndGet(v -> v + creditNumber);
                    diemTrungBinh.updateAndGet(v -> v + diemThangBon * creditNumber);
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

    YearClass getYearClass(String yearClassId) {
        Optional<YearClass> yearClassOptional = yearClassRepository.findById(yearClassId);
        if (yearClassOptional.isPresent()) {
            return yearClassOptional.get();
        } else return null;
    }

    @Override
    public StudentResult getDetail(String studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        StudentResult studentResult = new StudentResult();
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            studentResult.setStudent(student);
            studentResult.setYearClass(getYearClass(student.getYearClassId()));
            studentResult.setEducationProgramDTO(educationProgramService.getDetail(student.getEducationProgramId()));
            List<TermStudent> termStudentList = resultRepository.findAllByStudentId(studentId);
            System.out.println("termStudentList size: " + termStudentList.size());
            List<TermResult> termResultList = new ArrayList<>();
            termResultList = termStudentList.stream().map(termStudent -> {
                TermResult termResult = termStudent.toTermResult();
                Optional<Term> termOptional = termRepository.findById(termResult.getTermId());
                if (termOptional.isPresent()) {
                    termResult.setTerm(termOptional.get());
                }
                List<SubjectClassRegistration> subjectClassRegistrationList = subjectClassRegistrationRepository.findAllByStudentIdAndTermIdAndStatus(studentId, termResult.getTermId(), 1);
                List<SubjectClassRegistrationDTO> subjectClassRegistrationDTOList = subjectClassRegistrationList.stream()
                        .map(subjectClassRegistration -> {
                            SubjectClassRegistrationDTO subjectClassRegistrationDTO = subjectClassRegistration.toDTO();
                            Optional<Subject> subjectOptional = subjectClassRegistrationRepository.getSubjectBySubjectRegId(subjectClassRegistration.getId(), 1);
                            if (subjectOptional.isPresent()) {
                                Subject subject = subjectOptional.get();
                                subjectClassRegistrationDTO.setSubjectId(subject.getSubjectId());
                                subjectClassRegistrationDTO.setSubjectName(subject.getSubjectName());
                                subjectClassRegistrationDTO.setEachSubject(subject.getEachSubject());
                                subjectClassRegistrationDTO.setSubject(subject);
                            } else return null;
                            return subjectClassRegistrationDTO;
                        })
                        .filter(subjectClassRegistrationDTO -> subjectClassRegistrationDTO != null)
                        .collect(Collectors.toList());
                termResult.setSubjectClassRegistrationList(subjectClassRegistrationDTOList);
                return termResult;

            }).collect(Collectors.toList());
            studentResult.setResultDTOs(termResultList);
        }

        return studentResult;
    }
}
