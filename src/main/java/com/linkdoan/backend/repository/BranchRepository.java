package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.BranchDTO;
import com.linkdoan.backend.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, String> {

    Optional<Branch> findFirstByBranchId(String branchName);

    @Query(value =
            "SELECT new com.linkdoan.backend.dto.BranchDTO(branch.branchId, branch.branchName, branch.departmentId) " +
                    "FROM  Branch  branch " +
                    "WHERE (:departmentId is null or :departmentId = '' or branch.departmentId = :departmentId)"
    )
    List<BranchDTO> findAllByDepartmentId(@Param("departmentId") String departmentId);
}
