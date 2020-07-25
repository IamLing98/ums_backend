package com.linkdoan.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.linkdoan.backend.dto.CommonDTO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "country")
public class Country implements Serializable {
    @Id
    @Column(name = "country_id", columnDefinition = "char(10)", unique = true)
    private String countryId;

    @Column(name = "country_name", columnDefinition = "char(40)")
    private String countryName;

    @Column(name = "alpha2_code", columnDefinition = "char(3)")
    private String alpha2Code;

    @Column(name = "region", columnDefinition = "char(40)")
    private String region;

    @Column(name = "subregion", columnDefinition = "char(40)")
    private String subRegion;

    @Column(name = "timezones", columnDefinition = "char(15)")
    private String timezones;

    public CommonDTO toDTO() {
        CommonDTO commonDTO = new CommonDTO();
        commonDTO.setId(this.countryId);
        commonDTO.setLabel(this.countryName);
        return commonDTO;
    }
}
