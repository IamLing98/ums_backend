package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.model.Term;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TermService {

    public List<TermDTO> getAll(Integer year, Integer term );

    public Optional<Term> getDetail(String termId);

    public int create(TermDTO termDTO);

    public int update(String termId, TermDTO termDTO);

    public int delete(String id);
}
