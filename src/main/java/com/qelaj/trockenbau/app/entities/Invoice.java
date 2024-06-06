package com.qelaj.trockenbau.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date invoiceDate;
    private Date startWorkDate;
    private Date endWorkDate;
    @Column(length = 2000)
    private String invoiceDetails;
    private Double amount;
    private boolean paid;
    private String invoiceNumber;
    private Date invoicePaidDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    @JsonIgnore
    private Project project;
    @Transient
    private Long projectId;
}
