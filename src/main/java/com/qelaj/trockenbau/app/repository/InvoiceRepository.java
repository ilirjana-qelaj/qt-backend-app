package com.qelaj.trockenbau.app.repository;

import com.qelaj.trockenbau.app.entity.Client;
import com.qelaj.trockenbau.app.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Query(value="select i from Invoice i where i.project.id=?1")
    List<Invoice> findByProjectId(Long projectId);
}
