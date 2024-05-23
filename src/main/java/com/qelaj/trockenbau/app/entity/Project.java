package com.qelaj.trockenbau.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qelaj.trockenbau.app.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private Double amount;
    private Date startDate;
    private Date endDate;
    private String projectAddress;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId", referencedColumnName = "id")
    @JsonIgnore
    private Client client;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project")
    private List<Invoice> projectInvoices;
    @Transient
    private Long clientId;

}
