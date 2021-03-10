package com.linkdoan.backend.service;


import com.linkdoan.backend.dto.ResultDTO;

public interface ResultService {

    ResultDTO getResult(String termId, Integer rank);

}
