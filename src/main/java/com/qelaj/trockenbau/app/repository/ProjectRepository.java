package com.qelaj.trockenbau.app.repository;

import com.qelaj.trockenbau.app.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Query(value="select p from Project p where p.client.id=?1")
    List<Project> findByClientId(Long clientId);
}
