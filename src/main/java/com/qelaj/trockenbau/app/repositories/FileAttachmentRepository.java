package com.qelaj.trockenbau.app.repositories;


import com.qelaj.trockenbau.app.entities.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileAttachmentRepository extends JpaRepository<FileAttachment,Long> {
}
