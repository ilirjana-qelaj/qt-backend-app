package com.qelaj.trockenbau.app.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "File")
public class FileAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    @JsonSerialize(using = BlobSerializer.class)
    private Blob fileContent;
    private String fileDescription;

}
