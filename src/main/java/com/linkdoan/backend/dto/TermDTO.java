package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Term;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TermDTO {
    private String id;

    private Long year;

    private Long term;

    private Integer status;

    private Integer progress;

    private LocalDate progress11Date;

    private LocalDate progress12Date;

    private LocalDate progress21Date;

    private LocalDate progress22Date;

    private LocalDate progress31Date;

    private LocalDate progress32Date;

    public Term toModel(){
        Term term = new Term();
        term.setId(this.id);
        term.setStatus(this.status);
        term.setTerm(this.term);
        term.setYear(this.year);
        term.setProgress(this.progress);
        term.setProgress11Date(this.progress11Date);
        term.setProgress12Date(this.progress12Date);
        term.setProgress21Date(this.progress21Date);
        term.setProgress22Date(this.progress22Date);
        term.setProgress31Date(this.progress31Date);
        term.setProgress32Date(this.progress32Date);
        return term;
    }
}
