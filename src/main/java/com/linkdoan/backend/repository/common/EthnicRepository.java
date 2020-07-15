package com.linkdoan.backend.repository.common;

import com.linkdoan.backend.model.Ethnic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ethnicRepository")
public interface EthnicRepository extends JpaRepository<Ethnic, Integer> {
    List<Ethnic> findAllByCountryId(String countryId);
}
