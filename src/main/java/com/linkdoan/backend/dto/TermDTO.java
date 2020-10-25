package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Term;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TermDTO {
    private String id;

    private Long year;

    private Long term;

    private Integer status;

    public Term toModel(){
        Term term = new Term();
        term.setTerm(this.term);
        term.setYear(this.year);
        return term;
    }
}
