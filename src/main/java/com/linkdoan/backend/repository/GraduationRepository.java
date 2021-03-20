package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.GraduationDTO;
import com.linkdoan.backend.model.Graduation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraduationRepository extends JpaRepository<Graduation, Long> {

    @Query(
            value = "SELECT new com.linkdoan.backend.dto.GraduationDTO(graduation.id, graduation.schoolYearId, graduation.number)" +
                    "FROM Graduation graduation"
    )
    List<GraduationDTO> getAll();

}
