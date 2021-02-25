package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(
            value = "SELECT invoice.amount, invoice.creatorId, invoice.invoiceCreatedDate,invoice.invoiceName, invoice.invoiceNo, invoice.invoiceType, invoice.reasonId," +
                    "invoice.studentABN, invoice.termId, student.fullName, feeReason.reasonName, feeReason.reasonType , invoice.note " +
                    "FROM Invoice invoice INNER JOIN Student student ON invoice.studentABN = student.studentId " +
                    "INNER JOIN FeeReason feeReason ON invoice.reasonId = feeReason.id " +
                    "WHERE (invoice.termId = :termId )AND (:groupType IS NULL OR invoice.invoiceType = :groupType)  "
    )
    List<Object[]> getAllStudentInvoiceByType(@Param("groupType") Integer groupType, @Param("termId") String termId);
}
