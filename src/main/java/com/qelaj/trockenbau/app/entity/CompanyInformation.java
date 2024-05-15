package com.qelaj.trockenbau.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Company_Information")
public class CompanyInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String location;
    private String ownerName;
    private String ownerEmail;
    private String taxNumber;
    private String securityCode;


    public CompanyInformation(String name, String phone, String location, String ownerName, String ownerEmail) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
    }
}
