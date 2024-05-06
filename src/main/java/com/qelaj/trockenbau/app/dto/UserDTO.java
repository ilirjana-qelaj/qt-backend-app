package com.qelaj.trockenbau.app.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qelaj.trockenbau.app.entity.BlobSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;

    private String fullName;

    private String email;

    private String fileName;
    private String fileType;
    @Lob
    @JsonSerialize(using = BlobSerializer.class)
    private Blob fileContent;
}
