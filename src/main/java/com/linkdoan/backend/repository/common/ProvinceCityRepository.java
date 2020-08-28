package com.linkdoan.backend.repository.common;

import com.linkdoan.backend.model.ProvinceCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceCityRepository extends JpaRepository<ProvinceCity, String> {
    List<ProvinceCity> findAllByCountryId(String countryId);
}
