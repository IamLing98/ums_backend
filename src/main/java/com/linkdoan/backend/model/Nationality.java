package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.CommonDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "nationality")
public class Nationality {
    @Id
    @Column(name = "nationality_id", unique = true, columnDefinition = "int")
    private Integer nationalityId;

    @Column(name = "nationality_name", columnDefinition = "VARCHAR(45)")
    private String nationalityName;

    public CommonDTO toDTO() {
        CommonDTO commonDTO = new CommonDTO();
        commonDTO.setId(this.nationalityId.toString());
        commonDTO.setLabel(this.nationalityName.toString());
        return commonDTO;
    }
}
