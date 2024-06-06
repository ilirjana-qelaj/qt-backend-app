package com.qelaj.trockenbau.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firmName;
    private String clientName;
    private String telephoneNumber;
    private String clientAddress;
    private String clientEmail;
    private String financePhoneNumber;
    private String contactPersonNumber;
    private String contactPersonEmail;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "client")
    private List<Project> clientProjects;
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "client")
//    private List<Invoice> clientInvoices;
}
