package com.linkdoan.backend.service.common.Impl;

import com.linkdoan.backend.dto.CommonDTO;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.common.*;
import com.linkdoan.backend.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Qualifier("countryRepository")
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ProvinceCityRepository provinceCityRepository;

    @Qualifier("districtRepository")
    @Autowired
    DistrictRepository districtRepository;

    @Qualifier("communeRepository")
    @Autowired
    CommuneRepository communeRepository;

    @Qualifier("nationalityRepository")
    @Autowired
    NationalityRepository nationalityRepository;

    @Qualifier("ethnicRepository")
    @Autowired
    EthnicRepository ethnicRepository;

    @Autowired
    FeeReasonRepository feeReasonRepository;

    @Override
    public List<CommonDTO> getAllCountry() {
        List<Country> countryList = countryRepository.findAll();
        List<CommonDTO> commonDTOList = new ArrayList<>();
        for (int i = 0; i < countryList.size(); i++) {
            commonDTOList.add(countryList.get(i).toDTO());
        }
        return commonDTOList;
    }

    @Override
    public List<CommonDTO> getProvinceByCountryId(String keySearch) {
        List<ProvinceCity> provinceCityList = provinceCityRepository.findAllByCountryId(keySearch);
        List<CommonDTO> commonDTOList = new ArrayList<>();
        for (int i = 0; i < provinceCityList.size(); i++) {
            commonDTOList.add(provinceCityList.get(i).toDTO());
        }
        return commonDTOList;
    }

    @Override
    public List<CommonDTO> getDistrictByProvinceCityId(String keySearch) {
        List<District> districtList = districtRepository.findAllByProvinceCityId(keySearch);
        List<CommonDTO> commonDTOList = new ArrayList<>();
        for (int i = 0; i < districtList.size(); i++) {
            commonDTOList.add(districtList.get(i).toDTO());
        }
        return commonDTOList;
    }

    @Override
    public List<CommonDTO> getCommuneByDistrictId(String keySearch) {
        List<Commune> communeList = communeRepository.findAllByDistrictId(keySearch);
        List<CommonDTO> commonDTOList = new ArrayList<>();
        for (int i = 0; i < communeList.size(); i++) {
            commonDTOList.add(communeList.get(i).toDTO());
        }
        return commonDTOList;
    }

    @Override
    public List<CommonDTO> getAllNationality() {
        List<Nationality> nationalityList = nationalityRepository.findAll();
        List<CommonDTO> commonDTOList = new ArrayList<>();
        for (int i = 0; i < nationalityList.size(); i++) {
            commonDTOList.add(nationalityList.get(i).toDTO());
        }
        return commonDTOList;
    }

    @Override
    public List<CommonDTO> getAllEthnics() {
        List<Ethnic> ethnicList = ethnicRepository.findAll();
        List<CommonDTO> commonDTOList = new ArrayList<>();
        for (int i = 0; i < ethnicList.size(); i++) {
            commonDTOList.add(ethnicList.get(i).toDTO());
        }
        return commonDTOList;
    }

    @Override
    public List<FeeReason> getAllFeeReasons(Long type) {
        return feeReasonRepository.findAllByReasonType(type);
    }
}
