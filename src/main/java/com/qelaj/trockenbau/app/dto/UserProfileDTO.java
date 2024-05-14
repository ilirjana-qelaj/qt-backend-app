package com.qelaj.trockenbau.app.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qelaj.trockenbau.app.entity.BlobSerializer;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    private String fullName;
    private String email;
    private MultipartFile file;
    private String password;
}

