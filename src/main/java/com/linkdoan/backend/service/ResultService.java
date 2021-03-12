package com.linkdoan.backend.service;


import com.linkdoan.backend.dto.ResultDTO;
import com.linkdoan.backend.dto.StudentResult;

import java.util.List;

public interface ResultService {

    List<ResultDTO> getResult(String termId, Integer rank);

    StudentResult getDetail(String studentId);

    boolean calculatorResult(String termId);

}
