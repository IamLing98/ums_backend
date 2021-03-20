package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.EducationProgramDTO;
import com.linkdoan.backend.dto.EducationProgramSubjectDTO;
import com.linkdoan.backend.exception.FileStorageException;
import com.linkdoan.backend.model.EducationProgram;
import com.linkdoan.backend.model.EducationProgramSubject;
import com.linkdoan.backend.model.GroupStudent;
import com.linkdoan.backend.repository.*;
import com.linkdoan.backend.service.EducationProgramService;
import com.linkdoan.backend.service.FilesStorageService;
import com.linkdoan.backend.util.FileTypeConstants;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
        (
                propagation = Propagation.REQUIRED,
                readOnly = false,
                rollbackFor = Throwable.class
        )
public class EducationProgramServiceImpl implements EducationProgramService {

    @Autowired
    EducationProgramRepository educationProgramRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    EducationProgramSubjectRepository educationProgramSubjectRepository;

    @Qualifier("subjectRepository")
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FilesStorageService filesStorageService;

    @Override
    public EducationProgramDTO getDetailWithResult(String educationProgramId) {
        if (!educationProgramRepository.findById(educationProgramId).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        EducationProgramDTO educationProgramDTO = educationProgramRepository.getDetail(educationProgramId);
        //getList Subject By EP
        List<Object[]> subjectObjectList = educationProgramRepository.getCorrectListSubjectByEp(educationProgramId);
        List<Map<String, Object>> subjectListMap = new ArrayList<>();
        String[] labels = {"subjectId", "subjectName", "diemThangMuoi", "diemThangBon", "diemChu"};
        if (!subjectObjectList.isEmpty()) {
            for (Object[] object : subjectObjectList) {
                Map<String, Object> subjectMap = new HashMap<>();
                for (int i = 0; i < labels.length; i++) {
                    subjectMap.put(labels[i], object[i]);
                }
                List<Object[]> preLearnSubjectList = subjectRepository.getPreviousLearnSubject(object[7] + "");
                if (preLearnSubjectList != null && !preLearnSubjectList.isEmpty()) {
                    List<Map<String, Object>> preLearnList = new ArrayList<>();
                    for (Object[] preObject : preLearnSubjectList) {
                        Map<String, Object> preObjectMap = new HashMap<>();
                        preObjectMap.put("subjectId", preObject[0]);
                        preObjectMap.put("subjectName", preObject[1]);
                        preLearnList.add(preObjectMap);
                    }
                    subjectMap.put("preLearnSubjectList", preLearnList);
                } else {
                    subjectMap.put("preLearnSubjectList", new ArrayList<>());
                }
                subjectListMap.add(subjectMap);
            }
            //set Subject List
            educationProgramDTO.setSubjectList(subjectListMap);
        }
        return educationProgramDTO;
    }


    @Override
    public EducationProgramDTO getDetail(String educationProgramId) {
        if (!educationProgramRepository.findById(educationProgramId).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        EducationProgramDTO educationProgramDTO = educationProgramRepository.getDetail(educationProgramId);
        //getList Subject By EP
        List<Object[]> subjectObjectList = educationProgramRepository.getCorrectListSubjectByEp(educationProgramId);
        List<Map<String, Object>> subjectListMap = new ArrayList<>();
        if (!subjectObjectList.isEmpty()) {
            for (Object[] object : subjectObjectList) {
                Map<String, Object> subjectMap = new HashMap<>();
                subjectMap.put("departmentId", object[0]);
                subjectMap.put("discussNumber", object[1]);
                subjectMap.put("eachSubject", object[2]);
                subjectMap.put("exerciseNumber", object[3]);
                subjectMap.put("practiceNumber", object[4]);
                subjectMap.put("selfLearningNumber", object[5]);
                subjectMap.put("subjectForLevel", object[6]);
                subjectMap.put("subjectId", object[7]);
                subjectMap.put("subjectName", object[8]);
                subjectMap.put("theoryNumber", object[9]);
                subjectMap.put("departmentName", object[10]);
                subjectMap.put("term", object[11]);
                subjectMap.put("transactionType", object[12]);
                List<Object[]> preLearnSubjectList = subjectRepository.getPreviousLearnSubject(object[7] + "");
                if (preLearnSubjectList != null && !preLearnSubjectList.isEmpty()) {
                    List<Map<String, Object>> preLearnList = new ArrayList<>();
                    for (Object[] preObject : preLearnSubjectList) {
                        Map<String, Object> preObjectMap = new HashMap<>();
                        preObjectMap.put("subjectId", preObject[0]);
                        preObjectMap.put("subjectName", preObject[1]);
                        preLearnList.add(preObjectMap);
                    }
                    subjectMap.put("preLearnSubjectList", preLearnList);
                } else {
                    subjectMap.put("preLearnSubjectList", new ArrayList<>());
                }
                subjectListMap.add(subjectMap);
            }
            //set Subject List
            educationProgramDTO.setSubjectList(subjectListMap);
        }
        return educationProgramDTO;
    }

    @Override
    public List<EducationProgramDTO> getAllProgram(String branchId, String educationProgramId) {
        List<EducationProgramDTO> educationProgramDTOList = educationProgramRepository.findAll(branchId, educationProgramId);
        return educationProgramDTOList;
    }

    @Override
    public EducationProgramDTO create(EducationProgramDTO educationProgramDTO) throws FileNotFoundException {
        if (educationProgramRepository.findById(educationProgramDTO.getEducationProgramId()).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã tồn tại!!!");
        EducationProgram educationProgram = educationProgramDTO.toModel();
        educationProgram.setEducationProgramStatus(2);
        for (int i = 1; i <= educationProgramDTO.getTotalTerm(); i++) {
            GroupStudent groupStudent = new GroupStudent();
            groupStudent.setEducationProgramId(educationProgramDTO.getEducationProgramId());
            groupStudent.setTerm(i);
            groupRepository.save(groupStudent);
        }
        String fileName = educationProgramDTO.getFileName();
        Path filePath = filesStorageService.getPathFile(fileName);
        System.out.println("Path file: " + filePath.toString());
        if (!Files.exists(filePath)) throw new FileNotFoundException("File không hợp lệ");
        else {
            String postfix = filesStorageService.getPostfix(filePath.toString());
            System.out.println("Postfix: " + postfix);
            if (!(postfix.equals(FileTypeConstants.SPREAD_SHEET_2003) || postfix.equals(FileTypeConstants.SPREAD_SHEET_2007)))
                throw new FileStorageException("Định dạng file không đúng");
            InputStream inputFile = new FileInputStream(filePath.toString());
            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings(3).addListDelimiter(";").build();
            List<EducationProgramSubjectDTO> educationProgramSubjectList = Poiji
                    .fromExcel(inputFile, PoijiExcelType.XLSX, EducationProgramSubjectDTO.class, options);
            List<EducationProgramSubject> educationProgramList = educationProgramSubjectList.stream().map(educationProgramSubjectDTO -> {
                EducationProgramSubject educationProgramSubject = new EducationProgramSubject();
                educationProgramSubject.setEducationProgramId(educationProgram.getEducationProgramId());
                educationProgramSubject.setSubjectId(educationProgramSubjectDTO.getSubjectId());
                educationProgramSubject.setTerm(educationProgramSubjectDTO.getTerm());
                return educationProgramSubject;
            }).collect(Collectors.toList());
            System.out.println("Danh sach hoc phan: ");
            System.out.println(educationProgramList);
            educationProgramSubjectRepository.saveAll(educationProgramList);
        }
        return educationProgramRepository.save(educationProgram).toDTO();
    }

    @Override
    public EducationProgramDTO update(String id, EducationProgramDTO educationProgramDTO) {
        if (!educationProgramRepository.findById(id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        EducationProgram educationProgram = educationProgramDTO.toModel();
        educationProgram.setEducationProgramStatus(2);
        return educationProgramRepository.save(educationProgram).toDTO();
    }

    @Override
    public boolean delete(String id) {
        if (!educationProgramRepository.findById(id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        groupRepository.deleteByEducationProgramId(id);
        List<EducationProgramSubject> educationProgramSubjectList = educationProgramSubjectRepository.findAllByEducationProgramId(id);
        if (educationProgramSubjectList != null)
            educationProgramSubjectRepository.deleteAll(educationProgramSubjectList);
        educationProgramRepository.deleteById(id);
        return true;
    }

}
