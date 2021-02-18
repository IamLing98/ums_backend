package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.model.FeeCategory;
import com.linkdoan.backend.repository.FeeCategoriesRepository;
import com.linkdoan.backend.service.FeeCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeCategoriesServiceImpl implements FeeCategoriesService {

    @Autowired
    FeeCategoriesRepository feeCategoriesRepository;

    @Override
    public List<FeeCategory> getAll() {
        return feeCategoriesRepository.findAll();
    }
}
