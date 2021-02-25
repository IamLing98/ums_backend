package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.FeeCategoryDTO;
import com.linkdoan.backend.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

    @Query(
            value = "SELECT new com.linkdoan.backend.dto.FeeCategoryDTO(FC.id, FC.feeCategoryName, FCG.feeCategoryGroupName, FC.description, SFC.value)" +
                    "FROM Invoice I INNER JOIN InvoiceItem  II ON I.invoiceNo = II.invoiceNo " +
                    "INNER JOIN StudentsFeeCategory SFC ON II.studentCategoryId = SFC.id " +
                    "INNER JOIN FeeCategory FC ON SFC.feeCategoriesId = FC.id " +
                    "INNER JOIN FeeCategoryGroup FCG ON FC.feeCategoryGroupId = FCG.id " +
                    "WHERE I.invoiceNo = :invoiceNo"
    )
    List<FeeCategoryDTO> getListInvoiceItem(@Param("invoiceNo") Long invoiceNo);
}
