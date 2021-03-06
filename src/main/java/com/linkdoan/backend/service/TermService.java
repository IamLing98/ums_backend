package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.model.Term;

import java.util.List;

public interface TermService {

    List<TermDTO> getAll(Integer year, Integer term);

    Term getDetail(String termId);

    int create(TermDTO termDTO);

    int update(String termId, TermDTO termDTO, String userName);

    int delete(String id);
}
