package com.qelaj.trockenbau.app.repositories;

import com.qelaj.trockenbau.app.entities.CompanyInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyInformationRepository extends JpaRepository<CompanyInformation,Long> {
    @Query(value = "SELECT NEW CompanyInformation(name,phone,location,ownerName,ownerEmail) FROM CompanyInformation")
    List<CompanyInformation> getCompanyInformation();
}
