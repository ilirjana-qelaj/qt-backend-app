package com.qelaj.trockenbau.app.repository;

import com.qelaj.trockenbau.app.entity.Client;
import com.qelaj.trockenbau.app.entity.Invoice;
import com.qelaj.trockenbau.app.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Query(value="select i from Invoice i where i.project.id=?1")
    List<Invoice> findByProjectId(Long projectId);

    @Query("SELECT i FROM Invoice i WHERE (CAST(i.amount AS string) like %:value% OR cast(i.invoiceDate as string) like %:value% OR i.invoiceNumber like %:value%) AND i.project.id = :projectId")
    Page<Invoice> findInvoicesByValueAndProjectId(String value,Long projectId, Pageable pageable);


    @Query(value="select i from Invoice i where i.project.id=?1")
    Page<Invoice> findByProjectId(Long clientId, Pageable pageable);

}
