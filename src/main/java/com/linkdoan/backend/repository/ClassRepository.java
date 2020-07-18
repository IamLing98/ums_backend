package com.linkdoan.backend.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.linkdoan.backend.model.Class;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository("classRepository")
@Transactional(rollbackFor = Exception.class)
public interface ClassRepository  extends JpaRepository<Class, String> {

    Class findFirstByClassId(String classId);

    @Query( value = "SELECT Class.*  FROM Class   WHERE (:class_id is null or :class_id =''  or class_id = :class_id)"
            + "and (:department_id is null or :department_id ='' or department_id =  :department_id)"
            + "ORDER BY class_id ASC ",
            countQuery = "SELECT count(*) FROM Class WHERE (:class_id is null or :class_id =''  or class_id = :class_id) and (:department_id is null or :department_id ='' or department_id =:department_id)",
            nativeQuery = true
    )
    Page<Class> findBy(@Param("class_id") String classId, @Param("department_id") String departmentId , Pageable pageable);

    @Query( value = "SELECT next_val FROM Class WHERE (:class_id is null or :class_id ='' or class_id = :class_id)",
            nativeQuery = true
    )
    Optional<Integer> findNextValueSeqInClass(@Param("class_id") String classId);
}
