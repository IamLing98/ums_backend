package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.model.Term;
import com.linkdoan.backend.repository.NotificationsRepository;
import com.linkdoan.backend.repository.TermRepository;
import com.linkdoan.backend.repository.UserRepository;
import com.linkdoan.backend.service.NotificationsService;
import com.linkdoan.backend.service.SubjectClassRegistrationService;
import com.linkdoan.backend.service.SubjectRegistrationService;
import com.linkdoan.backend.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    TermRepository termRepository;

    @Autowired
    SubjectRegistrationService subjectRegistrationService;

    @Autowired
    SubjectClassRegistrationService subjectClassRegistrationService;

    @Autowired
    NotificationsRepository notificationsRepository;

    @Autowired
    TaskScheduler taskScheduler;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NotificationsService notificationsService;

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
        if (termOptional.isPresent()) {
            Term term = termOptional.get();
            TermDTO termDTO = term.toDTO();
            return termDTO;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "kHONG TON TAI TERM NAY!!!");
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

    int openSubjectSubmitting(Term term){
        LocalDate startDate =  LocalDate.of(25,12,12 );
        LocalDate endDate = LocalDate.of(25,12,13);
        LocalDateTime startDateLocalDateTime = startDate.atStartOfDay();
        LocalDateTime endDateLocalDateTime = endDate.atStartOfDay();
        long startDateLocalDateTimeMillis = startDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endDateLocalDateTimeLillis = endDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long differenceBetweenTwoDate = endDateLocalDateTimeLillis - startDateLocalDateTimeMillis;
        //create notification here
        Long senderId = 3L;
        List<Long> userList = userRepository.getUserIds();
        if(userList != null){
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã mở đăng ký học phần. <br/> Bắt đầu: "
                    + term.getSubjectSubmittingStartDate() + " Kết thúc: " + term.getSubjectSubmittingEndDate() );
        }

        taskScheduler.scheduleWithFixedDelay( () -> {

            System.out.println("ddONG DANG KY HOC PHAN");

        }, new Date( System.currentTimeMillis() + (10000) ), Long.MAX_VALUE );
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
                        term.setStatus(1);
                        termRepository.save(term);
                        openSubjectSubmitting(term);
//                        subjectRegistrationService.subjectSubmitForNewStudent(termId);
                        return 1;
                    }
                    break;
                case "SSOFF":
                    if (term.getProgress() == 12) {
                        term.setProgress(13);
                        term.setSubjectSubmittingEndDate(termDTO.getSubjectSubmittingEndDate());
                        termRepository.save(term);
                        return 1;
                    }
                    break;
                case "SCRON":
                    if (term.getProgress() == 13) {
                        term.setProgress(21);
                        term.setSubjectClassSubmittingStartDate(termDTO.getSubjectClassSubmittingStartDate());
                        term.setSubjectCLassSubmittingEndDate(termDTO.getSubjectCLassSubmittingEndDate());
                        term.setStatus(2);
                        subjectClassRegistrationService.subjectClassSubmitForNewStudent(termId);
                        termRepository.save(term);
                        return 1;
                    } else {
//                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Mã thời khoá biểu không hợp lệ!!!");
                    }
                    break;
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
