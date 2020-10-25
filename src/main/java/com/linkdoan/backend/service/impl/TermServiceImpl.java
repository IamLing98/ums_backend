package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.model.Term;
import com.linkdoan.backend.repository.TermRepository;
import com.linkdoan.backend.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TermServiceImpl implements TermService {
    @Autowired
    TermRepository termRepository ;

    public List<TermDTO> getAll(Integer year, Integer term ){
        List<Term> termList = termRepository.findAll();
        List<TermDTO> termDTOList = new ArrayList<>();
        for(int i = 0 ; i < termList.size(); i++){
            TermDTO temp = termList.get(i).toDTO();
            termDTOList.add(temp);
        }
        return termDTOList;
    }

    public int create(TermDTO termDTO){
        if(termRepository.findFirstByYearAndTerm(termDTO.getYear(), termDTO.getTerm()) != null)
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "Không thể thêm kỳ học này!!!");
        Term term = termDTO.toModel();
        term.setId(termDTO.getYear().toString() + termDTO.getTerm().toString());
        term.setStatus(2);
        termRepository.save(term);
        return 1;
    }

    public int update(TermDTO termDTO){
        if(termRepository.existsById((termDTO.getId())) == false) throw  new EntityNotFoundException("Not Found");
        termRepository.save(termDTO.toModel());
        return 1;
    }

    public int delete(Long id){
        if(termRepository.existsById(id) == false) throw new EntityNotFoundException("Not Found");
        termRepository.deleteById(id);
        return 1;
    }
}
