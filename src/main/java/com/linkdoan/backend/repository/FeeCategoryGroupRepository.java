package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.FeeCategoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeCategoryGroupRepository extends JpaRepository<FeeCategoryGroup, Long> {

    List<FeeCategoryGroup> findAllByFeeCategoryGroupTypeAndRole(Integer feeCategoryGroupType, String role);

}
