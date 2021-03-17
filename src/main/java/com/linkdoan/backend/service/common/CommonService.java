package com.linkdoan.backend.service.common;

import com.linkdoan.backend.dto.CommonDTO;
import com.linkdoan.backend.model.EmployeeCoefficientLevel;
import com.linkdoan.backend.model.FeeReason;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})
public interface CommonService {
    List<CommonDTO> getAllCountry();

    List<CommonDTO> getProvinceByCountryId(String keySearch);

    List<CommonDTO> getDistrictByProvinceCityId(String keySearch);

    List<CommonDTO> getCommuneByDistrictId(String keySearch);

    List<CommonDTO> getAllNationality();

    List<CommonDTO> getAllEthnics();

    List<FeeReason> getAllFeeReasons(Long type);

    List<EmployeeCoefficientLevel> getAllEmployeeCoefficientLevels(Long employeeLevelId);
}
