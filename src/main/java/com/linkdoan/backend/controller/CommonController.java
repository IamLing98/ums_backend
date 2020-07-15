package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.CommonDTO;
import com.linkdoan.backend.service.common.Impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CommonController {

    @Qualifier("commonService")
    @Autowired
    CommonServiceImpl commonService;

    @GetMapping("/country/findAll")
    public ResponseEntity getAllCountry(){
        return new ResponseEntity(commonService.getAllCountry(), HttpStatus.OK);
    }

    @GetMapping("/provinceCity/findByCountry")
    public ResponseEntity getProvinceByCountryId(@RequestParam(name="keySearch") String keySearch){
        return new ResponseEntity(commonService.getProvinceByCountryId(keySearch), HttpStatus.OK);
    }

    @GetMapping("/district/findByProvinceCityId")
    public ResponseEntity getDistrictByProvinceCityId(@RequestParam(name="keySearch") String keySearch){
        return new ResponseEntity(commonService.getDistrictByProvinceCityId(keySearch), HttpStatus.OK);
    }

    @GetMapping("/commune/findByDistrictId")
    public ResponseEntity getCommuneByDistrictId(@RequestParam(name="keySearch") String keySearch){
        return new ResponseEntity(commonService.getCommuneByDistrictId(keySearch), HttpStatus.OK);
    }

    @GetMapping("/ethnic/findByCountryId")
    public ResponseEntity getEthnicByCountryId(@RequestParam(name="keySearch") String keySearch){
        return new ResponseEntity(commonService.getEthnicByCountryId(keySearch), HttpStatus.OK);
    }

    @GetMapping("/nationality/findAll")
    public ResponseEntity getAllNationality(){
        return new ResponseEntity(commonService.getAllNationality(), HttpStatus.OK);
    }
}
