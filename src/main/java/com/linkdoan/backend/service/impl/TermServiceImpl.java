package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.NotificationDTO;
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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
        (
                propagation = Propagation.REQUIRED,
                readOnly = false,
                rollbackFor = Throwable.class
        )
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

    @Autowired
    SimpMessagingTemplate template;

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
    public Term getDetail(String termId) {
        Optional<Term> termOptional = termRepository.findFirstById(termId);
        if (termOptional.isPresent()) {
            Term term = termOptional.get();
            return term;
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

    int openSubjectSubmitting(Term term, String username) {
        LocalDateTime startDateLocalDateTime = term.getSubjectSubmittingStartDate();
        LocalDateTime endDateLocalDateTime = term.getSubjectSubmittingEndDate();
        long startDateLocalDateTimeMillis = startDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endDateLocalDateTimeLillis = endDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long senderId = 3L;
        List<Long> userList = userRepository.getUserIds();
        if (userList != null) {
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã mở đăng ký học phần. <br/> Bắt đầu: "
                    + term.getSubjectSubmittingStartDate() + " Kết thúc: " + term.getSubjectSubmittingEndDate());
        }
        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(13);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã kết thúc đăng ký học phần");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("SSOFF");
            notificationDTO.setSenderUsername("system");
            notificationDTO.setType(1L);
            notificationDTO.setReceiverUsername(username);
            template.convertAndSend("/topic/notifications", notificationDTO);
        }, new Date(System.currentTimeMillis() + 10000), Long.MAX_VALUE);
        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(12);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã bắt đầu đăng ký học phần.");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("SSON");
            notificationDTO.setSenderUsername("system");
            notificationDTO.setType(1L);
            notificationDTO.setReceiverUsername(username);
            template.convertAndSendToUser(username, "/queue/notifications", notificationDTO);
        }, new Date(startDateLocalDateTimeMillis), Long.MAX_VALUE);

        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(13);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã kết thúc đăng ký học phần");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("SSOFF");
            notificationDTO.setSenderUsername("system");
            notificationDTO.setType(1L);
            notificationDTO.setReceiverUsername(username);
            template.convertAndSendToUser(username, "/queue/notifications", notificationDTO);
        }, new Date(endDateLocalDateTimeLillis), Long.MAX_VALUE);
        return 1;
    }

    int openSubjectClassSubmitting(Term term, String username) {
        LocalDateTime startDateLocalDateTime = term.getSubjectClassSubmittingStartDate();
        LocalDateTime endDateLocalDateTime = term.getSubjectClassSubmittingStartDate();
        long startDateLocalDateTimeMillis = startDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endDateLocalDateTimeLillis = endDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long senderId = 3L;
        List<Long> userList = userRepository.getUserIds();
        if (userList != null) {
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã mở đăng ký lớp học phần. <br/> Bắt đầu: "
                    + startDateLocalDateTime + " Kết thúc: " + endDateLocalDateTime);
        }
        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(21);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã bắt đầu đăng ký lớp học phần.");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("SCRON");
            notificationDTO.setSenderUsername("system");
            notificationDTO.setType(1L);
            notificationDTO.setReceiverUsername(username);
            template.convertAndSendToUser(username, "/queue/notifications", notificationDTO);
        }, new Date(startDateLocalDateTimeMillis), Long.MAX_VALUE);

        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(22);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã kết thúc đăng ký lớp học phần");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("SSOFF");
            notificationDTO.setSenderUsername("system");
            notificationDTO.setType(1L);
            notificationDTO.setReceiverUsername(username);
            template.convertAndSendToUser(username, "/queue/notifications", notificationDTO);
        }, new Date(endDateLocalDateTimeLillis), Long.MAX_VALUE);
        return 1;
    }

    int openSubjectClassEditReg(Term term, String username) {
        LocalDateTime startDateLocalDateTime = term.getEditSubmittingStartDate();
        LocalDateTime endDateLocalDateTime = term.getEditSubmittingEndDate();
        long startDateLocalDateTimeMillis = startDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endDateLocalDateTimeLillis = endDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long senderId = 3L;
        List<Long> userList = userRepository.getUserIds();
        if (userList != null) {
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã mở đăng ký điều chỉnh. <br/> Bắt đầu: "
                    + startDateLocalDateTime + " Kết thúc: " + endDateLocalDateTime);
        }

        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(32);
            term.setStatus(3);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã kết thúc đăng ký điều chỉnh");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("SCREOFF");
            notificationDTO.setSenderUsername("system");
            notificationDTO.setType(1L);
            notificationDTO.setReceiverUsername(username);
            template.convertAndSendToUser(username, "/queue/notifications", notificationDTO);
        }, new Date(System.currentTimeMillis() + 5000), Long.MAX_VALUE);

        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(31);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã bắt đầu đăng ký điều chỉnh.");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("SCREON");
            notificationDTO.setSenderUsername("system");
            notificationDTO.setType(1L);
            notificationDTO.setReceiverUsername(username);
            template.convertAndSendToUser(username, "/queue/notifications", notificationDTO);
        }, new Date(startDateLocalDateTimeMillis), Long.MAX_VALUE);

        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(32);
            term.setStatus(3);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã kết thúc đăng ký điều chỉnh");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("SCREOFF");
            notificationDTO.setSenderUsername("system");
            notificationDTO.setType(1L);
            notificationDTO.setReceiverUsername(username);
            template.convertAndSendToUser(username, "/queue/notifications", notificationDTO);
        }, new Date(endDateLocalDateTimeLillis), Long.MAX_VALUE);
        return 1;
    }

    int settingTuitionFee(Term term, String username) {
        termRepository.save(term);
        LocalDateTime startDateLocalDateTime = term.getEditSubmittingStartDate();
        LocalDateTime endDateLocalDateTime = term.getEditSubmittingEndDate();
        long startDateLocalDateTimeMillis = startDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endDateLocalDateTimeLillis = endDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long senderId = 3L;
        List<Long> userList = userRepository.getUserIds();
        if (userList != null) {
            notificationsService.createNotification(senderId, userList, "Phòng Tổng hợp", "Thời gian nộp học phí: "
                    + startDateLocalDateTime + " Kết thúc: " + endDateLocalDateTime);
        }

        taskScheduler.scheduleWithFixedDelay(() -> {
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Phòng Tổng hợp", "Đã bắt đầu thu học phí");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("STFT");
            notificationDTO.setSenderUsername(username);
            notificationDTO.setType(1L);
            template.convertAndSend("/queue/notifications", notificationDTO);
        }, new Date(startDateLocalDateTimeMillis), Long.MAX_VALUE);

        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(34);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã kết thúc thời gian nộp học phí");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("STFT");
            notificationDTO.setSenderUsername(username);
            notificationDTO.setType(1L);
            template.convertAndSend("/queue/notifications", notificationDTO);
        }, new Date(endDateLocalDateTimeLillis), Long.MAX_VALUE);
        return 1;
    }

    @Override
    public int update(String termId, TermDTO termDTO, String username) {
        Optional<Term> termOptional = termRepository.findById(termId);
        if (termOptional.isPresent()) {
            Term term = termOptional.get();
            String actionType = termDTO.getActionType();
            switch (actionType) {
                case "SSON":
                    if (term.getProgress() == 11) {
                        term.setProgress(12);
                        //use truncated to fixed default time is 00:00:00
//                        term.setSubjectSubmittingStartDate(termDTO.getSubjectSubmittingStartDate().truncatedTo(ChronoUnit.HOURS));
//                        term.setSubjectSubmittingEndDate(termDTO.getSubjectSubmittingEndDate().truncatedTo(ChronoUnit.HOURS));
                        LocalDateTime startTime = termDTO.getSubjectSubmittingStartDate();
                        LocalDateTime endTime = termDTO.getSubjectSubmittingEndDate();
                        LocalDateTime ldtNow = LocalDateTime.now();
                        if (startTime.isBefore(ldtNow) || endTime.isBefore(ldtNow) || endTime.isBefore(startTime))
                            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Thời gian không hợp lệ");
                        term.setSubjectSubmittingStartDate(termDTO.getSubjectSubmittingStartDate());
                        term.setSubjectSubmittingEndDate(termDTO.getSubjectSubmittingEndDate());
                        term.setStatus(1);
                        termRepository.save(term);
                        openSubjectSubmitting(term, username);
                        subjectRegistrationService.subjectSubmitForNewStudent(termId);
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
                        LocalDateTime startTime = termDTO.getSubjectClassSubmittingStartDate();
                        LocalDateTime endTime = termDTO.getSubjectCLassSubmittingEndDate();
                        LocalDateTime ldtNow = LocalDateTime.now();
                        if (startTime.isBefore(ldtNow) || endTime.isBefore(ldtNow) || endTime.isBefore(startTime))
                            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Thời gian không hợp lệ");
                        term.setSubjectClassSubmittingStartDate(termDTO.getSubjectClassSubmittingStartDate());
                        term.setSubjectCLassSubmittingEndDate(termDTO.getSubjectCLassSubmittingEndDate());
                        term.setStatus(2);
                        openSubjectClassSubmitting(term, username);
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
                        LocalDateTime startTime = termDTO.getEditSubmittingStartDate();
                        LocalDateTime endTime = termDTO.getEditSubmittingEndDate();
                        LocalDateTime ldtNow = LocalDateTime.now();
                        if (startTime.isBefore(ldtNow) || endTime.isBefore(ldtNow) || endTime.isBefore(startTime))
                            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Thời gian không hợp lệ");
                        term.setEditSubmittingStartDate(termDTO.getEditSubmittingStartDate());
                        term.setEditSubmittingEndDate(termDTO.getEditSubmittingEndDate());
                        openSubjectClassEditReg(term, username);
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
                case "STFT":
                    if (term.getProgress() == 32) {
                        term.setProgress(33);
                        LocalDateTime startTime = termDTO.getTuitionFeeStartDate();
                        LocalDateTime endTime = termDTO.getTuitionFeeEndDate();
                        LocalDateTime ldtNow = LocalDateTime.now();
                        if (startTime.isBefore(ldtNow) || endTime.isBefore(ldtNow) || endTime.isBefore(startTime))
                            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Thời gian không hợp lệ");
                        term.setTuitionFeeStartDate(startTime);
                        term.setTuitionFeeEndDate(endTime);
                        termRepository.save(term);
//                        settingTuitionFee();
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
