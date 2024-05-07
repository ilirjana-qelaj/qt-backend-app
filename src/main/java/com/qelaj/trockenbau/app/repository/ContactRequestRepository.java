package com.qelaj.trockenbau.app.repository;

import com.qelaj.trockenbau.app.entity.ContactRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRequestRepository extends JpaRepository<ContactRequest,Long> {
}
