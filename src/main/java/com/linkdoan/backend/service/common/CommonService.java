package com.linkdoan.backend.service.common;

import com.linkdoan.backend.dto.CommonDTO;
import com.linkdoan.backend.model.Commune;
import com.linkdoan.backend.model.Country;
import com.linkdoan.backend.model.District;
import com.linkdoan.backend.model.ProvinceCity;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface CommonService {
    List<Country> getAllCountry();
    List<ProvinceCity> getProvinceByCountryId(String keySearch);
    List<District> getDistrictByProvinceCityId(String keySearch);
    List<Commune> getCommuneByDistrictId(String keySearch);
}
