package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @Qualifier("commonService")
    @Autowired
    CommonService commonService;

    @GetMapping("/country/findAll")
    public ResponseEntity getAllCountry(){
        return new ResponseEntity(commonService.getAllCountry(), HttpStatus.OK);
    }

    @GetMapping("/provinceCities")
    public ResponseEntity getProvinceByCountryId(@RequestParam(name="countryId") String countryId){
        return new ResponseEntity(commonService.getProvinceByCountryId(countryId), HttpStatus.OK);
    }

    @GetMapping("/district/findByProvinceCityId")
    public ResponseEntity getDistrictByProvinceCityId(@RequestParam(name="keySearch") String keySearch){
        return new ResponseEntity(commonService.getDistrictByProvinceCityId(keySearch), HttpStatus.OK);
    }

    @GetMapping("/commune/findByDistrictId")
    public ResponseEntity getCommuneByDistrictId(@RequestParam(name="keySearch") String keySearch){
        return new ResponseEntity(commonService.getCommuneByDistrictId(keySearch), HttpStatus.OK);
    }

    @GetMapping("/ethnics")
    public ResponseEntity getAllEthnics(){
        return new ResponseEntity(commonService.getAllEthnics(), HttpStatus.OK);
    }

    @GetMapping("/nationality/findAll")
    public ResponseEntity getAllNationality(){
        return new ResponseEntity(commonService.getAllNationality(), HttpStatus.OK);
    }
}
