package com.qelaj.trockenbau.app.repository;

import com.qelaj.trockenbau.app.entity.CompanyInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInformationRepository extends JpaRepository<CompanyInformation,Long> {
}
