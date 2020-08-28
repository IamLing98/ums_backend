package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.CommonDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "commune")
public class Commune implements Serializable {
    @Id
    @Column(name = "commune_id", columnDefinition = "VARCHAR(5)", unique = true)
    private String communeId;

    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "type", columnDefinition = "VARCHAR(30)")
    private String type;

    @Column(name = "district_id", columnDefinition = "VARCHAR(5)")
    private String districtId;

    public CommonDTO toDTO() {
        CommonDTO commonDTO = new CommonDTO();
        commonDTO.setId(this.communeId);
        commonDTO.setLabel(this.name);
        return commonDTO;
    }
}
