package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.TermDTO;

import java.util.List;

public interface TermService {

    public List<TermDTO> getAll(Integer year, Integer term );

    public TermDTO getDetail(String termId);

    public int create(TermDTO termDTO);

    public int update(String termId, TermDTO termDTO);

    public int delete(String id);
}
