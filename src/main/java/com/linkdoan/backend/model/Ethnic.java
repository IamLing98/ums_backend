package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.CommonDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="ethnic")
public class Ethnic {
    @Id
    @Column(name="ethnic_id", unique = true, columnDefinition = "int")
    private Integer ethnicId;

    @Column(name="ethnic_name",  columnDefinition = "varchar(45)")
    private String ethnicName;

    @Column(name="country_id",  columnDefinition = "char(10)")
    private String countryId;

    public CommonDTO toDTO(){
        CommonDTO commonDTO = new CommonDTO();
        commonDTO.setId(this.ethnicId.toString());
        commonDTO.setLabel(this.ethnicName);
        return commonDTO;
    }

}
