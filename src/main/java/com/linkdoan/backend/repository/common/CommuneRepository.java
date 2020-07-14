package com.linkdoan.backend.repository.common;

import com.linkdoan.backend.model.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("communeRepository")
public interface CommuneRepository extends JpaRepository<Commune, String> {
    List<Commune> findAllByDistrictId(String districtId);
}
