package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.base.dto.FileDTO;
import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.exception.FileStorageException;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.model.primaryKey.DepartmentCourseNextVal;
import com.linkdoan.backend.repository.*;
import com.linkdoan.backend.service.FilesStorageService;
import com.linkdoan.backend.service.StudentService;
import com.linkdoan.backend.util.FileTypeConstants;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("studentService")
@Transactional
        (
                propagation = Propagation.REQUIRED,
                readOnly = false,
                rollbackFor = Throwable.class
        )
public class StudentServiceImpl implements StudentService {

    private static final String STUDENT = "Student";

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Qualifier("studentRepository")
    @Autowired
    private StudentRepository studentRepository;

    @Qualifier("yearClassRepository")
    @Autowired
    private YearClassRepository yearClassRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private EducationProgramRepository educationProgramRepository;

    @Autowired
    private DepartmentCourseNextValRepository departmentCourseNextValRepository;

    @Qualifier("subjectRepository")
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentSubjectRepository studentSubjectRepository;

    @Autowired
    FilesStorageService fileStorageService;

    @Override
    public List<StudentDTO> findBy(Integer page, Integer pageSize, String studentId, Integer startYear, String classId, String departmentId) throws IOException {
//        if(page == null) page = 0;
        // Pageable pageable = Pageable.unpaged();
//        if(pageSize == null) pageSize = 999999;
//        Pageable pageable = PageRequest.of(page, pageSize);
        //logger.info(studentDTO.getStartYear().toString());
//        return studentRepository.findBy(studentDTO.getKeySearch1(), studentDTO.getKeySearch2(), studentDTO.getKeySearch3(), studentDTO.getKeySearch4(), studentDTO.getKeySearch5(), pageable);
        List<StudentDTO> studentDTOList = studentRepository.getAllStudent();
        return studentDTOList;
    }

    @Override
    public StudentDTO getDetail(String studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            StudentDTO studentDTO = studentRepository.getDetail(studentId);
            return studentDTO;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
    }


    @Override
    public int create(List<StudentDTO> studentDTOS) {
        int count = 0;

        List<Student> studentList = new ArrayList<>();

        List<StudentSubject> studentSubjectList = new ArrayList<>();

        for (StudentDTO studentDTO : studentDTOS) {
            Optional<Department> departmentOptional = departmentRepository.findById(studentDTO.getDepartmentId());
            Optional<EducationProgram> educationProgramOptional = educationProgramRepository.findById(studentDTO.getEducationProgramId());
            if (departmentOptional.isPresent() && educationProgramOptional.isPresent()) {
                Department department = departmentOptional.get();
                EducationProgram educationProgram = educationProgramOptional.get();
                String educationProgramId = educationProgram.getEducationProgramId();
                Integer courseNumber;
                if (studentDTO.getCourseNumber() != null) {
                    courseNumber = studentDTO.getCourseNumber();
                } else if (studentDTO.getYearClassId() != null) {
                    Optional<YearClass> yearClassOptional = yearClassRepository.findById(studentDTO.getYearClassId());
                    if (yearClassOptional.isPresent()) {
                        YearClass yearClass = yearClassOptional.get();
                        courseNumber = yearClass.getCourseNumber();
                    } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Khoá học không hợp lệ!!!");
                } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Khoá học không hợp lệ!!!");
                DepartmentCourseNextVal departmentCourseNextValOptional = departmentCourseNextValRepository.findDistinctFirstByDepartmentIdAndAndCourseNumber(department.getDepartmentId(), courseNumber);
                if (departmentCourseNextValOptional == null)
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Khoá học không tồn tại!!!");
                Integer nextIdValue = departmentCourseNextValOptional.getNextStudentValue();
                String nextIdValueString = "";
                if (nextIdValue < 10) {
                    nextIdValueString = "00" + nextIdValue;
                } else if (nextIdValue < 100 && nextIdValue > 10) {
                    nextIdValueString = "0" + nextIdValue;
                } else if (nextIdValue < 1000 && nextIdValue > 100) {
                    nextIdValueString = "" + nextIdValue;
                }
                Integer modNowYear = (department.getStartYear() + courseNumber) % 2000;
                modNowYear--;
                String subDepartmentId = department.getDepartmentId().substring(4, 7);
                String studentId = "5" + modNowYear + subDepartmentId + nextIdValueString;
                System.out.println(studentId);
                studentDTO.setStudentId(studentId);
                studentDTO.setCreatedDate(LocalDate.now());
                Student student = studentDTO.toModel();
                studentList.add(student);
                //find list subject add to student
                List<Subject> subjectList = subjectRepository.findAllByEducationProgramId(educationProgramId);
                subjectList.stream().forEach(subject -> {
                    StudentSubject studentSubject = new StudentSubject();
                    studentSubject.setEducationProgramId(educationProgramId);
                    studentSubject.setStudentId(studentId);
                    studentSubject.setSubjectId(subject.getSubjectId());
                    studentSubjectList.add(studentSubject);
                });
                departmentCourseNextValOptional.setNextClassValue(departmentCourseNextValOptional.getNextStudentValue() + 1);
                departmentCourseNextValRepository.save(departmentCourseNextValOptional);
                count++;
            }
        }
        studentSubjectRepository.saveAll(studentSubjectList);
        studentRepository.saveAll(studentList);
        return count;
    }

    //    @Override
//    public Student insertStudent(StudentDTO studentDTO) throws ParseException {
//        Student studentModel = studentDTO.toModel();
//        String classId = studentDTO.getClassId();
//        String departmentId = studentDTO.getDepartmentId();
//        String studentId = "";
//        Optional<YearClass> optionalClass = classRepository.findFirstByClassId(classId);
//        if (!optionalClass.isPresent()) throw new EntityNotFoundException("Lớp học không tồn tại !!!");
//        else {
//            YearClass classModel = optionalClass.get();
//            Integer subDepartmentId = Integer.parseInt(departmentId.substring(4));
//            Integer currentNumber = classModel.getStartYear() % 1000;
//            Integer nextVal = classModel.getNextVal();
//            if ((int) nextVal / 100 >= 1)
//                studentId = "5" + currentNumber.toString() + subDepartmentId.toString() + String.format("%00d", nextVal);
//            else if (nextVal / 10 >= 1)
//                studentId = "5" + currentNumber.toString() + subDepartmentId.toString() + String.format("%01d", nextVal);
//            else
//                studentId = "5" + currentNumber.toString() + subDepartmentId.toString() + String.format("%02d", nextVal);
//        }
//        studentModel.setStudentId(studentId);
//        studentModel.setStatus(1);
//        java.time.LocalDate date = java.time.LocalDate.now();
//        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
//        studentModel.setStartSchool(sqlDate);
//
//        return studentRepository.save(studentModel);
//    }
//
    @Override
    public List<StudentDTO> update(List<StudentDTO> studentDTOList) throws IOException {
        for (StudentDTO studentDTO : studentDTOList) {
            Optional<Student> studentOptional = studentRepository.findById(studentDTO.getStudentId());
            if (studentOptional.isPresent()) {
                Student student = studentDTO.toModel();
                studentRepository.save(student);
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sinh viên không tồn tại!!!" + studentDTO.getStudentId());
        }
        return studentDTOList;
    }

    @Override
    public int delete(List<String> ids) {
        int count = 0;
        for (String id : ids) {
            Optional<Student> studentOptional = studentRepository.findById(id);
            if (studentOptional.isPresent()) {
                studentRepository.deleteById(id);
                count++;
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sinh viên không tồn tại!!!" + id);
        }
        return count;
    }

    @Override
    public List<StudentDTO> importStudents(FileDTO fileDTO) throws FileNotFoundException {
        List<StudentDTO> studentDTOList;
        String fileName = fileDTO.getFileName();
        Path filePath = fileStorageService.getPathFile(fileName);
        System.out.println("Path file: " + filePath.toString());
        if (!Files.exists(filePath)) throw new FileNotFoundException("File không hợp lệ");
        else {
            String postfix = fileStorageService.getPostfix(filePath.toString());
            System.out.println("Postfix: " + postfix);
            if (!(postfix.equals(FileTypeConstants.SPREAD_SHEET_2003) || postfix.equals(FileTypeConstants.SPREAD_SHEET_2007)))
                throw new FileStorageException("Định dạng file không đúng");
            InputStream inputFile = new FileInputStream(filePath.toString());
            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings(6).addListDelimiter(";").build();
            List<Student> studentList = Poiji
                    .fromExcel(inputFile, PoijiExcelType.XLSX, Student.class, options);
            List<EducationProgram> educationProgramList = educationProgramRepository.findAllByEducationProgramType(1);
            for (Student student : studentList) {
                for (EducationProgram educationProgram : educationProgramList) {
                    if (educationProgram.getEducationProgramName().equals(student.getEducationProgramName())) {
                        student.setEducationProgramId(educationProgram.getEducationProgramId());
                    }
                }
            }
            System.out.println("student list");
            System.out.println(studentList.get(0));
            studentRepository.saveAll(studentList);
            studentDTOList = studentList.stream().map(student -> student.toDTO()).collect(Collectors.toList());
        }
        return studentDTOList;
    }


}
