package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.CommonDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "district")
@Entity
public class District {
    @Id
    @Column(name = "district_id", columnDefinition = "VARCHAR(5)", unique = true)
    private String districtId;

    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "type", columnDefinition = "VARCHAR(30)")
    private String type;

    @Column(name = "province_city_id", columnDefinition = "VARCHAR(5)")
    private String provinceCityId;

    public CommonDTO toDTO() {
        CommonDTO commonDTO = new CommonDTO();
        commonDTO.setId(this.districtId);
        commonDTO.setLabel(this.name);
        return commonDTO;
    }
}
