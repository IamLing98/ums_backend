package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.StudentsFeeCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentFeeTuitionRepository extends JpaRepository<StudentsFeeCategories, Long> {

    @Query(value =
            "SELECT FC.id, FC.feeCategoryName, FC.feeCategoryName, FC.frequency, SFC.isPaid, SFC.value " +
                    "FROM StudentsFeeCategories  SFC INNER JOIN FeeCategory FC ON SFC.feeCategoriesId = FC.id " +
                    "INNER JOIN FeeCategoryGroup FCG ON FC.feeCategoryGroupId = FCG.id " +
                    "WHERE FCG.feeCategoryGroupType = 0 AND FCG.role = :role AND FCG.id = :feeCategoryGroupId AND SFC.studentId = :studentId and SFC.termId = :termId"
    )
    List<Object[]> findAllByStudentIdAndTermIdAndFeeCategoryGroupId(@Param("studentId") String studentId,
                                                                    @Param("termId") String termId,
                                                                    @Param("feeCategoryGroupId") Long feeCategoryGroupId,
                                                                    @Param("role") String role);

    Optional<StudentsFeeCategories> findFirstByStudentIdAndTermIdAndFeeCategoriesId(String studentId, String termId, Long feeCategoriesId);
}
