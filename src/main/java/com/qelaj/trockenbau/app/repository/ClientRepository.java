package com.qelaj.trockenbau.app.repository;

import com.qelaj.trockenbau.app.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE lower(c.firmName) like %:value% OR lower(c.clientName) like %:value% OR lower(c.telephoneNumber) like %:value% OR lower(c.clientAddress) like %:value%")
    List<Client> findClientsByValue(String value);

}
