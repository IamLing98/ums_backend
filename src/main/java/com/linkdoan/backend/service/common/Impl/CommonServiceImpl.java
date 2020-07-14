package com.linkdoan.backend.service.common.Impl;

import com.linkdoan.backend.dto.CommonDTO;
import com.linkdoan.backend.model.Commune;
import com.linkdoan.backend.model.Country;
import com.linkdoan.backend.model.District;
import com.linkdoan.backend.model.ProvinceCity;
import com.linkdoan.backend.repository.common.CommuneRepository;
import com.linkdoan.backend.repository.common.CountryRepository;
import com.linkdoan.backend.repository.common.DistrictRepository;
import com.linkdoan.backend.repository.common.ProvinceCityRepository;
import com.linkdoan.backend.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Qualifier("countryRepository")
    @Autowired
    CountryRepository countryRepository;

    @Qualifier("provinceRepository")
    @Autowired
    ProvinceCityRepository provinceCityRepository;

    @Qualifier("districtRepository")
    @Autowired
    DistrictRepository districtRepository;

    @Qualifier("communeRepository")
    @Autowired
    CommuneRepository communeRepository;

    @Override
    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    @Override
    public List<ProvinceCity> getProvinceByCountryId(String keySearch) {
        return provinceCityRepository.findAllByCountryId(keySearch);
    }

    @Override
    public List<District> getDistrictByProvinceCityId(String keySearch) {
        return districtRepository.findAllByProvinceCityId(keySearch);
    }

    @Override
    public List<Commune> getCommuneByDistrictId(String keySearch) {
        return communeRepository.findAllByDistrictId(keySearch);
    }
}
