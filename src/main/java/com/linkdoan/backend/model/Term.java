package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.TermDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name="progress")
    private Integer progress;

    @Column(name="progress11_date")
    private LocalDate progress11Date;

    @Column(name="progress12_date")
    private LocalDate progress12Date;

    @Column(name="progress21_date")
    private LocalDate progress21Date;

    @Column(name="progress22_date")
    private LocalDate progress22Date;

    @Column(name="progress31_date")
    private LocalDate progress31Date;

    @Column(name="progress32_date")
    private LocalDate progress32Date;

    public TermDTO toDTO(){
        TermDTO termDTO = new TermDTO();
        termDTO.setId(this.id);
        termDTO.setStatus(this.status);
        termDTO.setTerm(this.term);
        termDTO.setYear(this.year);
        termDTO.setProgress(this.progress);
        termDTO.setProgress11Date(this.progress11Date);
        termDTO.setProgress12Date(this.progress12Date);
        termDTO.setProgress21Date(this.progress21Date);
        termDTO.setProgress22Date(this.progress22Date);
        termDTO.setProgress31Date(this.progress31Date);
        termDTO.setProgress32Date(this.progress32Date);
        return termDTO;
    }

}