package com.qelaj.trockenbau.app.repositories;

import com.qelaj.trockenbau.app.entities.ContactRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRequestRepository extends JpaRepository<ContactRequest,Long> {

    @Query("SELECT c FROM ContactRequest c WHERE (c.requestFromName like %:value% OR c.requestFromSurname like %:value% OR c.requestFromEmail like %:value% or c.requestFromNumber like %:value% )")
    Page<ContactRequest> findContactRequestByValueAndProjectId(String value, Pageable pageable);

}
