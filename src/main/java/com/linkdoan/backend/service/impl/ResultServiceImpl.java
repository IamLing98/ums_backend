package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.ResultDTO;
import com.linkdoan.backend.service.ResultService;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

    @Override
    public ResultDTO getResult(String termId, Integer rank) {
        return new ResultDTO();
    }
}
