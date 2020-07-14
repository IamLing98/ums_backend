package com.linkdoan.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name="district")
@Entity
public class District {
    @Id
    @Column(name="district_id", columnDefinition = "VARCHAR(5)", unique = true)
    private String districtId;

    @Column(name="name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name="type", columnDefinition = "VARCHAR(30)")
    private String type;

    @Column(name="province_city_id", columnDefinition = "VARCHAR(5)")
    private String provinceCityId;

}
