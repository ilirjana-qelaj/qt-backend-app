package com.qelaj.trockenbau.app.repositories;

import com.qelaj.trockenbau.app.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Query(value="select p from Project p where p.client.id=?1")
    List<Project> findByClientId(Long clientId);

    @Query("SELECT p FROM Project p " +
            "WHERE (lower(p.projectName) like lower(concat('%', :value, '%')) " +
            "OR lower(p.projectAddress) like lower(concat('%', :value, '%')) " +
            "OR str(p.amount) like %:value% " +
            "OR lower(p.status) like lower(concat('%', :value, '%'))) " +
            "AND p.client.id = :clientId")
    Page<Project> findProjectsByValueAndClientId(String value, Long clientId, Pageable pageable);

    @Query(value="select p from Project p where p.client.id=?1")
    Page<Project> findByClientId(Long clientId, Pageable pageable);
}
