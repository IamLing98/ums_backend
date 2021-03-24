package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.SchoolYearDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "school_year")
public class SchoolYear {

    @Id
    @Column(name = "id")
    private String schoolYearId;

    @Column(name = "start")
    private Integer start;

    @Column(name = "end")
    private Integer end;

    public SchoolYearDTO toDTO(){
        SchoolYearDTO schoolYearDTO= new SchoolYearDTO();
        schoolYearDTO.setSchoolYearId(schoolYearId);
        schoolYearDTO.setStart(start);
        schoolYearDTO.setEnd(end);
        return schoolYearDTO;
    }
}
