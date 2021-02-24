package com.linkdoan.backend.repository.common;

import com.linkdoan.backend.model.FeeReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeReasonRepository extends JpaRepository<FeeReason, Long> {

    @Query(
            value= "SELECT feeReason FROM FeeReason feeReason WHERE :type IS NULL OR feeReason.reasonType = :type "
    )
    List<FeeReason> findAllByReasonType(Long type);
}
