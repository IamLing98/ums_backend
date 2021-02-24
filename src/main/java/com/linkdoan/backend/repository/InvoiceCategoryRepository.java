package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Invoice;
import com.linkdoan.backend.model.InvoiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceCategoryRepository extends JpaRepository<InvoiceCategory, Long> {
}
