package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.EducationProgramDTO;
import com.linkdoan.backend.model.EducationProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EducationProgramRepository extends JpaRepository<EducationProgram, String> {

    @Query(value="SELECT   new com.linkdoan.backend.dto.EducationProgramDTO( ep.educationProgramId, ep.educationProgramName, ep.educationProgramLevel," +
            " branch.branchId, ep.educationProgramType, ep.educationProgramStatus, branch.branchName, ep.totalTerm) " +
            "FROM EducationProgram  ep inner join Branch branch on ep.branchId = branch.branchId " +
            "WHERE (ep.educationProgramId = :educationProgramId) "
    )
    EducationProgramDTO findDTOById(@Param("educationProgramId") String educationProgramId);

    List<EducationProgram> findAll();

   EducationProgram findFirstByEducationProgramId(String id);

    @Query(value="SELECT   new com.linkdoan.backend.dto.EducationProgramDTO( ep.educationProgramId, ep.educationProgramName, ep.educationProgramLevel," +
            "branch.branchId, ep.educationProgramType, ep.educationProgramStatus, branch.branchName ,ep.totalTerm) " +
            "FROM EducationProgram  ep inner join Branch branch on ep.branchId = branch.branchId " +
            "WHERE (:branchId is null or :branchId = '' or ep.branchId = :branchId) and (:educationProgramId is null or :educationProgramId ='' or ep.educationProgramId = :educationProgramId) "
    )
    List<EducationProgramDTO> findAll(@Param("branchId") String branchId, @Param("educationProgramId") String educationProgramId );
}
