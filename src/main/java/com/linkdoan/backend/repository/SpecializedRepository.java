package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Specialized;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializedRepository extends JpaRepository<Specialized, String> {

    @Query(value =
            "SELECT Specialized.*  FROM Specialized   WHERE (:specialized_id is null or :specialized_id =''  or specialized_id = :specialized_id)"
            + "and (:department_id is null or :department_id =\"\" or department_id =  :department_id)"
            + "ORDER BY specialized_id ASC ",
            countQuery = "SELECT count(*) FROM Specialized WHERE (:specialized_id is null or :specialized_id =''  or specialized_id = :specialized_id)"
                    + " and (:department_id is null or :department_id ='' or department_id =:department_id)",
            nativeQuery = true
    )
    Page<Specialized> findBy(@Param("specialized_id") String specializedId, @Param("department_id") String departmentId, Pageable pageable);
}
