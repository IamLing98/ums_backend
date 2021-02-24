package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.*;
import com.linkdoan.backend.service.TuitionFeeService;
import com.linkdoan.backend.util.FeeCategoryABNConstants;
import com.linkdoan.backend.util.MoneyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TuitionFeeServiceImpl implements TuitionFeeService {

    @Autowired
    FeeCategoriesRepository feeCategoriesRepository;

    @Autowired
    StudentFeeTuitionRepository studentFeeTuitionRepository;

    @Autowired
    SubjectClassRegistrationRepository subjectClassRegistrationRepository;

    @Autowired
    FeeCategoryGroupRepository feeCategoryGroupRepository;

    @Qualifier("studentRepository")
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TermRepository termRepository;

    @Override
    public List<?> debug() {
        List<Subject> subjectList = new ArrayList<>();
        Term term = new Term();
        term.setId("20202");
        term.setEachSubjectFee(370000D);
        term.setMultipleNumber(1.7);
        term.setFirstExamFee(30000D);
        insertNewFeeForStudentInTerm(term);
        return subjectList;
    }

    //code 1 lan xong vut =)))))))))
    public List<Subject> insertNewFeeForStudentInTerm(Term term) {
        String termId = term.getId();
        Double eachSubjectFeeValue = term.getEachSubjectFee();
        Double multipleSubjectFeeValue = term.getMultipleNumber();
        Double firstExamFeeValue = term.getFirstExamFee();
        System.out.println("eachSubjectFeeValue: " + eachSubjectFeeValue + " multipleSubjectFeeValue: " + multipleSubjectFeeValue);
        List<StudentDTO> studentDTOList = feeCategoriesRepository.getAllStudent();
        System.out.println("So luong sinh vien: " + studentDTOList.size());
        List<FeeCategoryGroup> feeCategoryGroupList = feeCategoryGroupRepository.findAllByFeeCategoryGroupTypeAndRole(0, FeeCategoryABNConstants.STUDENT_FEE_ROLE);
        List<StudentsFeeCategories> studentsFeeCategoriesList = new ArrayList<>();
        if (feeCategoryGroupList != null && !feeCategoryGroupList.isEmpty()) {
            for (StudentDTO student : studentDTOList) {
                String studentId = student.getStudentId();
                Integer currentTerm = student.getYearClass().getCurrentTerm();
                List<Subject> subjectSubmittedList = subjectClassRegistrationRepository.getListSubmittedSubjectOfStudentInTerm(studentId, termId);
                if (subjectSubmittedList.size() > 0) {
                    System.out.println("StudentID: " + studentId + " has " + subjectSubmittedList.size() + " subject");
                }
                if (subjectSubmittedList != null && !subjectSubmittedList.isEmpty()) {
                    Double tuitionFeeValue = 0D;
                    if (subjectSubmittedList.size() == 1) {
                        tuitionFeeValue = subjectSubmittedList.get(0).getEachSubject().doubleValue() * eachSubjectFeeValue * multipleSubjectFeeValue + firstExamFeeValue;
                    } else {
                        tuitionFeeValue = subjectSubmittedList.stream()
                                .map(subject -> subject.getEachSubject().doubleValue() * eachSubjectFeeValue * multipleSubjectFeeValue + firstExamFeeValue)
                                .reduce(0D, Double::sum);
                    }
                    System.out.println("Fee: " + tuitionFeeValue);
                    System.out.println("So luong group type: " + feeCategoryGroupList.size());
                    for (FeeCategoryGroup feeCategoryGroup : feeCategoryGroupList) {
                        Long feeGroupTypeId = feeCategoryGroup.getId();
                        String feeGroupABN = feeCategoryGroup.getFeeCategoryGroupTypeABN();
                        if (feeGroupABN.equals(FeeCategoryABNConstants.INSURANCE_FEE_GROUP_ABN)) {

                        } else {
                            List<FeeCategory> feeCategoryList = feeCategoriesRepository.findAllByFeeCategoryGroupId(feeGroupTypeId);
                            if (feeCategoryList != null && !feeCategoryList.isEmpty()) {
                                System.out.println("feeCategoryList size: " + feeCategoryList.size());
                                for (FeeCategory feeCategory : feeCategoryList) {
                                    Double feeCategoryDoubleValue = feeCategory.getValue();
                                    StudentsFeeCategories studentsFeeCategories = new StudentsFeeCategories();
                                    studentsFeeCategories.setFeeCategoriesId(feeCategory.getId());
                                    studentsFeeCategories.setStudentId(student.getStudentId());
                                    studentsFeeCategories.setIsPaid(0);
                                    studentsFeeCategories.setTermId(termId);
                                    if (feeCategory.getFrequency().equals(FeeCategoryABNConstants.ONCE)) {
                                        if (currentTerm == 0) {
                                            studentsFeeCategories.setValue(feeCategoryDoubleValue.longValue());
                                            studentsFeeCategoriesList.add(studentsFeeCategories);
                                        }
                                    } else if (feeCategory.getFrequency().equals(FeeCategoryABNConstants.EVERY_YEAR)) {
                                        if (currentTerm % 2 == 0) {
                                            studentsFeeCategories.setValue(feeCategoryDoubleValue.longValue());
                                            studentsFeeCategoriesList.add(studentsFeeCategories);
                                        }
                                    } else if (feeCategory.getFrequency().equals(FeeCategoryABNConstants.EVERY_TERM)) {
                                        if (feeCategory.getFeeCategoryABN().equals(FeeCategoryABNConstants.TUITION_FEE_ABN)) {
                                            System.out.println("Day la hoc phi: " + tuitionFeeValue.longValue());
                                            studentsFeeCategories.setValue(tuitionFeeValue.longValue());
                                            studentsFeeCategoriesList.add(studentsFeeCategories);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            studentFeeTuitionRepository.saveAll(studentsFeeCategoriesList);
        } else System.out.println("feeCategoryList: isEmpty!!!");
        return null;
    }


    @Override
    public Map<String, Object> getFeeInfoOfStudent(String studentId, String termId) {
        String[] labels = {"id", "feeCategoryName", "feeCategoryName", "frequency", "isPaid", "value"};
        Map<String, Object> rs = new HashMap<>();
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Term> termOptional = termRepository.findById(termId);
        if (termOptional.isPresent()) {
            Term term = termOptional.get();
            rs.put("term", term);
        }
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            rs.put("student", student);
            Long totalFee = 0L;
            List<FeeCategoryGroup> feeCategoryGroupsList = feeCategoryGroupRepository.findAllByFeeCategoryGroupTypeAndRole(0, FeeCategoryABNConstants.STUDENT_FEE_ROLE);
            List<Map<String, Object>> feeCategoryGroupMapList = new ArrayList<>();
            for (FeeCategoryGroup feeCategoryGroup : feeCategoryGroupsList) {
                Map<String, Object> feeCategoryGroupMap = new HashMap<>();
                feeCategoryGroupMap.put("feeCategoryGroupId", feeCategoryGroup.getId());
                feeCategoryGroupMap.put("feeCategoryGroupName", feeCategoryGroup.getFeeCategoryGroupName());
                feeCategoryGroupMap.put("feeCategoryGroupType", feeCategoryGroup.getFeeCategoryGroupType());
                feeCategoryGroupMap.put("feeCategoryGroupABN", feeCategoryGroup.getFeeCategoryGroupTypeABN());
                feeCategoryGroupMap.put("feeCategoryGroupRole", feeCategoryGroup.getRole());
                List<Object[]> feeCategoryObjectArrayList = studentFeeTuitionRepository.findAllByStudentIdAndTermIdAndFeeCategoryGroupId(studentId, termId, feeCategoryGroup.getId(), "STUDENT");
                if (feeCategoryObjectArrayList != null && !feeCategoryObjectArrayList.isEmpty()) {
                    List<Map<String, Object>> listCategory = new ArrayList<>();
                    for (Object[] object : feeCategoryObjectArrayList) {
                        totalFee += (Long) object[5];
                        Map<String, Object> stringObjectMap = new HashMap<>();
                        for (int i = 0; i < labels.length; i++) {
                            stringObjectMap.put(labels[i], object[i]);
                        }
                        listCategory.add(stringObjectMap);
                    }
                    feeCategoryGroupMap.put("feeCategoryList", listCategory);
                } else {
                    feeCategoryGroupMap.put("feeCategoryList", new ArrayList<>());
                }
                feeCategoryGroupMapList.add(feeCategoryGroupMap);
            }
            rs.put("totalFee", totalFee);
            MoneyUtil moneyUtil = new MoneyUtil();
            rs.put("totalFeeText", moneyUtil.readNum(totalFee.toString()));
            rs.put("feeCategoryGroupList", feeCategoryGroupMapList);
        }
        return rs;
    }
}
