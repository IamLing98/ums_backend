package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInvoiceRepository extends JpaRepository<Invoice, Long> {

}
