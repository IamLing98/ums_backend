package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.model.Schedule;
import com.linkdoan.backend.model.SubjectClassRegistration;
import com.linkdoan.backend.model.Term;
import com.linkdoan.backend.repository.ScheduleRepository;
import com.linkdoan.backend.repository.TermRepository;
import com.linkdoan.backend.service.SubjectClassRegistrationService;
import com.linkdoan.backend.service.SubjectRegistrationService;
import com.linkdoan.backend.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    TermRepository termRepository;

    @Autowired
    SubjectRegistrationService subjectRegistrationService;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    SubjectClassRegistrationService subjectClassRegistrationService;


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
    public TermDTO getDetail(String termId) {
        Optional<Term> termOptional = termRepository.findFirstById(termId);
        if(termOptional.isPresent()){
            Term term = termOptional.get();
            TermDTO termDTO = term.toDTO();
            Optional<Schedule> scheduleOptional = scheduleRepository.findFirstByTermIdAndIsActive(termId,1);
            if(scheduleOptional.isPresent()){
                Schedule schedule = scheduleOptional.get();
                termDTO.setActiveSchedule(schedule.getId());
            }
            return termDTO;
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "kHONG TON TAI TERM NAY!!!");
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
        Optional<Term> termOptional = termRepository.findById(termId);
        if (termOptional.isPresent()) {
            Term term = termOptional.get();
            String actionType = termDTO.getActionType();
            switch (actionType) {
                    case "SSON":
                    if (term.getProgress() == 11) {
                        term.setProgress(12);
                        term.setSubjectSubmittingStartDate(termDTO.getSubjectSubmittingStartDate());
                        term.setSubjectSubmittingEndDate(termDTO.getSubjectSubmittingEndDate());
                        termRepository.save(term);
                        subjectRegistrationService.subjectSubmitForNewStudent(termId);
                        return 1;
                    }break;
                case "SSOFF":
                    if (term.getProgress() == 12) {
                        term.setProgress(13);
                        term.setSubjectSubmittingEndDate(termDTO.getSubjectSubmittingEndDate());
                        termRepository.save(term);
                        return 1;
                    } break;
                case "SCRON":
                    if (term.getProgress() == 13) {
                        Optional<Schedule> scheduleOptional = scheduleRepository.findById(termDTO.getActiveSchedule());
                        if (scheduleOptional.isPresent()) {
                            Schedule schedule = scheduleOptional.get();
                            if (schedule.getTermId().equals(term.getId())) {
                                term.setProgress(21);
                                term.setSubjectClassSubmittingStartDate(termDTO.getSubjectClassSubmittingStartDate());
                                term.setSubjectCLassSubmittingEndDate(termDTO.getSubjectCLassSubmittingEndDate());
                                schedule.setIsActive(1);
                                termRepository.save(term);
                                System.out.println("Save term");
                                scheduleRepository.save(schedule);
                                System.out.println("Active schedule: " +  termDTO.getActiveSchedule());
                                subjectClassRegistrationService.subjectClassSubmitForNewStudent(termId,termDTO.getActiveSchedule());
                                return 1;
                            } else {
//                                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Mã thời khoá biểu không hợp lệ!!!");
                            }
                        } else {
//                            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Mã thời khoá biểu không hợp lệ!!!");
                        }
                    } else {
//                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Không đúng tiến trình");
                    }break;
                case "SCROFF":
                    if (term.getProgress() == 21) {
                        term.setProgress(22);
                        termRepository.save(term);
                        return 1;
                    } else {

                    }
                    break;
                case "SCREON":
                    if (term.getProgress() == 22) {
                        term.setProgress(31);
                        term.setEditSubmittingStartDate(termDTO.getSubjectSubmittingStartDate());
                        term.setEditSubmittingEndDate(termDTO.getEditSubmittingEndDate());
                        termRepository.save(term);
                        return 1;
                    } else {

                    }
                    break;
                case "SCREOFF":
                    if (term.getProgress() == 31) {
                        term.setProgress(32);
                        termRepository.save(term);
                        return 1;
                    } else {

                    }
                    break;
                default:
            }
        }
        return 0;
    }

    @Override
    public int delete(String id) {
        if (termRepository.existsById(id) == false)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Học kỳ không tồn tại!!!");
        termRepository.deleteById(id);
        return 1;
    }
}
