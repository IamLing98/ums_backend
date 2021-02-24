package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.model.Term;

import java.util.List;

public interface TermService {

    public List<TermDTO> getAll(Integer year, Integer term);

    public Term getDetail(String termId);

    public int create(TermDTO termDTO);

    public int update(String termId, TermDTO termDTO, String userName);

    public int delete(String id);
}
