package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.NotificationDTO;
import com.linkdoan.backend.dto.StudentSubjectDTO;
import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.*;
import com.linkdoan.backend.service.*;
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
import java.util.stream.Collectors;

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

    @Autowired
    ResultService resultService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    StudentGraduationRepository studentGraduationRepository;

    @Autowired
    EducationProgramRepository educationProgramRepository;

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
        List<Student> studentList = studentRepository.findAll();
        List<TermStudent> termStudentList = new ArrayList<>();
        termStudentList = studentList.stream().map(student -> {
            TermStudent termStudent = new TermStudent();
            termStudent.setTermId(term.getId());
            termStudent.setStudentId(student.getStudentId());
            return termStudent;
        }).collect(Collectors.toList());
        resultRepository.saveAll(termStudentList);
        termRepository.save(term);
        return 1;
    }

    int openSubjectSubmitting(Term term, String username) {
        LocalDateTime startDateLocalDateTime = term.getSubjectSubmittingStartDate();
        LocalDateTime endDateLocalDateTime = term.getSubjectSubmittingEndDate();
        long startDateLocalDateTimeMillis = startDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endDateLocalDateTimeLillis = endDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        String senderId = "system";
        List<String> userList = userRepository.getUserIds();
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
        String senderId = "system";
        List<String> userList = userRepository.getUserIds();
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
        String senderId = "system";
        List<String> userList = userRepository.getUserIds();
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
        String senderId = "system";
        List<String> userList = userRepository.getUserIds();
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

    int openInputGrade(Term term, String username) {
        LocalDateTime startDateLocalDateTime = term.getInputGradeStartDate();
        LocalDateTime endDateLocalDateTime = term.getInputGradeEndDate();
        long startDateLocalDateTimeMillis = startDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endDateLocalDateTimeLillis = endDateLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        String senderId = "system";
        List<String> userList = userRepository.getUserIds();
        if (userList != null) {
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã mở nhập điểm. <br/> Bắt đầu: "
                    + startDateLocalDateTime + " Kết thúc: " + endDateLocalDateTime);
        }

        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(36);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã đóng nhập điểm");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("STGGR");
            notificationDTO.setSenderUsername("system");
            notificationDTO.setType(1L);
            notificationDTO.setReceiverUsername(username);
            template.convertAndSendToUser(username, "/queue/notifications", notificationDTO);
        }, new Date(System.currentTimeMillis() + 5000), Long.MAX_VALUE);

        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(35);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã mở nhập điểm.");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("STGGR");
            notificationDTO.setSenderUsername("system");
            notificationDTO.setType(1L);
            notificationDTO.setReceiverUsername(username);
            template.convertAndSendToUser(username, "/queue/notifications", notificationDTO);
        }, new Date(startDateLocalDateTimeMillis), Long.MAX_VALUE);

        taskScheduler.scheduleWithFixedDelay(() -> {
            term.setProgress(36);
            termRepository.save(term);
            notificationsService.createNotification(senderId, userList, "Đào tạo", "Đã đóng nhập điểm");
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setContent("STGGR");
            notificationDTO.setSenderUsername("system");
            notificationDTO.setType(1L);
            notificationDTO.setReceiverUsername(username);
            template.convertAndSendToUser(username, "/queue/notifications", notificationDTO);
        }, new Date(endDateLocalDateTimeLillis), Long.MAX_VALUE);
        return 1;
    }

    private int createPredictGraduationList(Term term) {
        List<Student> studentList = studentGraduationRepository.findAllStudents();
        List<StudentGraduation> predictStudentGraduationList = new ArrayList<>();

        studentList.parallelStream().forEach(student -> {
            Optional<EducationProgram> educationProgramOptional = educationProgramRepository.findById(student.getEducationProgramId());
            if (educationProgramOptional.isPresent()) {
                EducationProgram educationProgram = educationProgramOptional.get();
                Integer totalMustLearnSubject = educationProgram.getTotalMustLearnSubject();
                Integer totalSelectLearnSubject = educationProgram.getTotalSelectLearnSubject();
                Integer studentTotalMustLearnSubject = 0;
                Integer studentTotalSelectLearnSubject = 0;
                Double totalGrade = 0D;
                Integer totalEachSubject = 0;
                List<StudentSubjectDTO> subjectList = studentGraduationRepository.findAllSubjectGoodResult(student.getStudentId(), student.getEducationProgramId());
                for (StudentSubjectDTO subject : subjectList) {
                    EducationProgramSubject educationProgramSubject = studentGraduationRepository
                            .findEducationProgramSubjectByEducationProgramIdAndSubjectId(subject.getSubjectId(), student.getEducationProgramId());
                    if (educationProgramSubject != null && educationProgramSubject.getTransactionType() == 1)
                        studentTotalMustLearnSubject += subject.getSubject().getEachSubject();
                    else if (educationProgramSubject != null && educationProgramSubject.getTransactionType() == 0)
                        studentTotalSelectLearnSubject += subject.getSubject().getEachSubject();
                    totalEachSubject += subject.getSubject().getEachSubject();
                    totalGrade += subject.getDiemTrungBinh() != null ? subject.getDiemTrungBinh() : 0;
                }

                if (studentTotalMustLearnSubject >= totalMustLearnSubject && studentTotalSelectLearnSubject >= totalSelectLearnSubject) {
                    System.out.print("Tao moi mot sinh vien du kien: ");
                    System.out.println(student);
                    StudentGraduation studentGraduation = new StudentGraduation();
                    studentGraduation.setEducationProgramId(educationProgram.getEducationProgramId());
                    studentGraduation.setStatus(1);
                    studentGraduation.setStudentId(student.getStudentId());
                    studentGraduation.setTermId(term.getId());
                    studentGraduation.setTotalEachSubject(totalEachSubject);
                    Double CPA = totalGrade / totalEachSubject;
                    studentGraduation.setCPA(CPA);
                    studentGraduation.setRankValue(getRank(CPA));
                    predictStudentGraduationList.add(studentGraduation);
                }
            }
        });
        if (predictStudentGraduationList != null) {
            studentGraduationRepository.saveAll(predictStudentGraduationList);
        }
        return predictStudentGraduationList.size();
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
                case "STGGR":
                    //start time for input student grade
                    if (term.getProgress() == 34) {
                        term.setProgress(35);
                        LocalDateTime startTime = termDTO.getInputGradeStartDate();
                        LocalDateTime endTime = termDTO.getInputGradeEndDate();
                        LocalDateTime ldtNow = LocalDateTime.now();
                        if (startTime.isBefore(ldtNow) || endTime.isBefore(ldtNow) || endTime.isBefore(startTime))
                            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Thời gian không hợp lệ");
                        term.setInputGradeStartDate(startTime);
                        term.setInputGradeEndDate(endTime);
                        termRepository.save(term);
                    }

                case "GETRESULT":
                    if (term.getProgress() == 36) {
                        term.setProgress(37);
                        LocalDateTime ldtNow = LocalDateTime.now();
                        term.setResultCreatedDate(ldtNow);
                        resultService.calculatorResult(termId);
                        termRepository.save(term);
                    }
                case "GETPREDICTGRADUATION":
                    if (term.getProgress() == 37) {
                        term.setProgress(38);
                        LocalDateTime ldtNow = LocalDateTime.now();
                        term.setResultCreatedDate(ldtNow);
                        createPredictGraduationList(term);
                        termRepository.save(term);
                    }
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
