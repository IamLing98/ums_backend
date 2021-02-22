package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.FeeCategoriesRepository;
import com.linkdoan.backend.repository.FeeCategoryGroupRepository;
import com.linkdoan.backend.repository.StudentFeeTuitionRepository;
import com.linkdoan.backend.repository.SubjectClassRegistrationRepository;
import com.linkdoan.backend.service.TuitionFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<?> debug() {
        List<Subject> subjectList = new ArrayList<>();
        Term term = new Term();
        insertNewFeeForStudentInTerm(term);
        return subjectList;
    }

    private Double calTuiTuitionFeeValue(List<Subject> subjectList, Double eachSubjectFeeValue, Double multipleSubjectFeeValue) {
        Double rs = 0D;
        rs = subjectList.stream()
                .map(subject -> subject.getEachSubject().doubleValue() * eachSubjectFeeValue * multipleSubjectFeeValue)
                .reduce(0D, Double::sum);
        return rs;
    }

    public List<Subject> insertNewFeeForStudentInTerm(Term term) {
        String termId = term.getId();
        Double eachSubjectFeeValue = term.getEachSubjectFee();
        Double multipleSubjectFeeValue = term.getMultipleNumber();
        List<StudentDTO> studentDTOList = feeCategoriesRepository.getAllStudent();
        List<FeeCategoryGroup> feeCategoryGroupList = feeCategoryGroupRepository.findAllByFeeCategoryGroupType(0);
        List<StudentsFeeCategories> studentsFeeCategoriesList = new ArrayList<>();
        if (feeCategoryGroupList != null && !feeCategoryGroupList.isEmpty()) {
            for (StudentDTO student : studentDTOList) {
                String studentId = student.getStudentId();
                Integer currentTermOfStudent = student.getYearClass().getCurrentTerm();
                List<Subject> subjectSubmitted = subjectClassRegistrationRepository.getListSubmittedSubjectOfStudentInTerm(studentId, termId);
                // tuition fee by subject submitted
                if (subjectSubmitted != null && subjectSubmitted.size() > 0) { //check if null
                    Double tuitionFeeValue = calTuiTuitionFeeValue(subjectSubmitted, eachSubjectFeeValue, multipleSubjectFeeValue);
                    for (FeeCategoryGroup feeCategoryGroup : feeCategoryGroupList) {
                        Long feeGroupTypeId = feeCategoryGroup.getId();
                        String feeGroupABN = feeCategoryGroup.getFeeCategoryGroupTypeABN();
                        List<FeeCategory> listFeeCategoryOfGroup = feeCategoriesRepository.findAllByFeeCategoryGroupId(feeGroupTypeId);
                        if (listFeeCategoryOfGroup != null && !listFeeCategoryOfGroup.isEmpty()) {
                            listFeeCategoryOfGroup.stream().
                        }
                    }
                }
            }
            studentFeeTuitionRepository.saveAll(studentsFeeCategoriesList);
        } else System.out.println("feeCategoryList: isEmpty!!!");
        return null;
    }

    @Override
    public List<Map<String, Object>> getStudentList() {
        return null;
    }

    @Override
    public Map<String, Object> getFeeInfoOfStudent(String studentId) {
        return null;
    }
}
