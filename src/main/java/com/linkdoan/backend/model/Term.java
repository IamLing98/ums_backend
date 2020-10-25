package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.TermDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "term")
@Data
public class Term {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "year")
    private Long year;

    @Column(name = "term")
    private Long term;

    @Column(name="status")
    private Integer status;

    public TermDTO toDTO(){
        TermDTO termDTO = new TermDTO();
        termDTO.setId(this.id);
        termDTO.setStatus(this.status);
        termDTO.setTerm(this.term);
        termDTO.setYear(this.year);
        return termDTO;
    }

}