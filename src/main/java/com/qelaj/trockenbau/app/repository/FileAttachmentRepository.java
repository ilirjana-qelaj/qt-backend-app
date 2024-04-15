package com.qelaj.trockenbau.app.repository;


import com.qelaj.trockenbau.app.entity.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileAttachmentRepository extends JpaRepository<FileAttachment,Long> {
}
