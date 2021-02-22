package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.model.FeeCategoryGroup;
import com.linkdoan.backend.repository.FeeCategoriesRepository;
import com.linkdoan.backend.repository.FeeCategoryGroupRepository;
import com.linkdoan.backend.service.FeeCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeCategoriesServiceImpl implements FeeCategoriesService {

    @Autowired
    FeeCategoriesRepository feeCategoriesRepository;

    @Autowired
    FeeCategoryGroupRepository feeCategoryGroupRepository;

    @Override
    public List<FeeCategoryGroup> getAll() {
        return feeCategoryGroupRepository.findAll();
    }
}
