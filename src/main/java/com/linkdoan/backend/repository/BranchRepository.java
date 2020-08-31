package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, String> {

    Branch findFirstByBranchId(String branchName);
}
