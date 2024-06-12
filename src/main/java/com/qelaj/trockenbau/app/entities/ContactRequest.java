package com.qelaj.trockenbau.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Contact_Requests")
public class ContactRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Timestamp requestDate;
    private String requestFromName;
    private String requestFromSurname;
    private String requestFromEmail;
    private String requestFromNumber;
    private String requestDetails;
    private boolean opened;

}
