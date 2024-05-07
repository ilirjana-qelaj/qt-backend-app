package com.qelaj.trockenbau.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Contact_Request")
public class ContactRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date requestDate;
    private String requestFromName;
    private String requestFromSurname;
    private String requestFromEmail;
    private String requestFromNumber;
    private String requestDetails;
    private boolean opened;

}
