package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.model.Term;
import com.linkdoan.backend.repository.TermRepository;
import com.linkdoan.backend.service.SubjectRegistrationService;
import com.linkdoan.backend.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    TermRepository termRepository;

    @Autowired
    SubjectRegistrationService subjectRegistrationService;

    @Override
    public List<TermDTO> getAll(Integer year, Integer term) {
        List<Term> termList = termRepository.findAll();
        List<TermDTO> termDTOList = new ArrayList<>();
        for (int i = 0; i < termList.size(); i++) {
            TermDTO temp = termList.get(i).toDTO();
            termDTOList.add(temp);
        }
        return termDTOList;
    }

    @Override
    public Optional<Term> getDetail(String termId) {
        return termRepository.findFirstById(termId);
    }

    @Override
    public int create(TermDTO termDTO) {
        if (termRepository.findFirstByYearAndTerm(termDTO.getYear(), termDTO.getTerm()) != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Không thể thêm kỳ học này!!!");
        Term term = termDTO.toModel();
        term.setId(termDTO.getYear().toString() + termDTO.getTerm().toString());
        term.setStatus(2);
        term.setProgress(11);
        termRepository.save(term);
        return 1;
    }

    @Override
    public int update(String termId, TermDTO termDTO) {
        Optional<Term> termOptional = termRepository.findById(termDTO.getId());
        if (termOptional.isPresent()) {
            Term term = termOptional.get();
            switch (termDTO.getActionType()) {
                case "SSON":
                    if (term.getProgress() == 11) {
                        term.setProgress(12);
                        term.setSubjectSubmittingStartDate(termDTO.getSubjectSubmittingStartDate());
                        term.setSubjectSubmittingEndDate(termDTO.getSubjectSubmittingEndDate());
                        termRepository.save(term);
                        subjectRegistrationService.subjectSubmitForNewStudent(termId);
                    } else {
                        throw new ResponseStatusException(HttpStatus.CHECKPOINT, "Không đúng tiến trình");
                    }
                    break;
                case "SSOFF":
                    if (term.getProgress() == 12) {
                        term.setProgress(13);
                        term.setSubjectSubmittingEndDate(termDTO.getSubjectSubmittingEndDate());
                        termRepository.save(term);
                    } else {
                        throw new ResponseStatusException(HttpStatus.CHECKPOINT, "Không đúng tiến trình");
                    }
                    break;
                case "SCSON":
                    if (term.getProgress() == 13) {
                        term.setProgress(21);
                    } else {

                    }
                    break;
                case "SCSOFF":
                    if (term.getProgress() == 21) {
                        term.setProgress(22);
                    } else {

                    }
                    break;
                case "ESON":
                    if (term.getProgress() == 23) {
                        term.setProgress(31);
                    } else {

                    }
                    break;
                case "ESOFF":
                    if (term.getProgress() == 31) {
                        term.setProgress(32);
                    } else {

                    }
                    break;
                default:
            }
        }
        return 1;
    }

    @Override
    public int delete(String id) {
        if (termRepository.existsById(id) == false)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Học kỳ không tồn tại!!!");
        termRepository.deleteById(id);
        return 1;
    }
}
