package com.linkdoan.backend.service;


import com.linkdoan.backend.dto.ResultDTO;

import java.util.List;

public interface ResultService {

    List<ResultDTO> getResult(String termId, Integer rank);

    boolean calculatorResult(String termId);

}
