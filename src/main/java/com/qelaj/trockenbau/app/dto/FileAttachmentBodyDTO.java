package com.qelaj.trockenbau.app.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileAttachmentBodyDTO {
    private String fileDescriptionEN;
    private String fileHeaderEN;
    private String fileDescriptionDE;
    private String fileHeaderDE;
}
