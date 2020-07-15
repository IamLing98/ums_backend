package com.linkdoan.backend.repository.common;

import com.linkdoan.backend.model.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("nationalityRepository")
public interface NationalityRepository  extends JpaRepository<Nationality, Integer> {

}
