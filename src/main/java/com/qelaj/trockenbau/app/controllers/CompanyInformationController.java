package com.qelaj.trockenbau.app.controllers;

import com.qelaj.trockenbau.app.entities.CompanyInformation;
import com.qelaj.trockenbau.app.services.CompanyInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/company-information")
public class CompanyInformationController {

    private final CompanyInformationService companyInformationService;

    @Autowired
    public CompanyInformationController(CompanyInformationService companyInformationService) {
        this.companyInformationService = companyInformationService;
    }


    @GetMapping("/id/{id}")
    private ResponseEntity getCompanyInformationById(@PathVariable Long id){
        Optional<CompanyInformation> client = companyInformationService.getCompanyInformation(id);
        if(client.isPresent()){
            return ResponseEntity.ok(client);
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }

    @GetMapping("/all")
    private ResponseEntity getAllCompanyInformation(){
        return ResponseEntity.ok(companyInformationService.getAllCompanyInformation());
    }

    @GetMapping("/contact")
    private ResponseEntity getCompanyInformationForContact(){
        return ResponseEntity.ok(companyInformationService.getCompanyInformationForContact());
    }


    @PatchMapping("/update")
    private ResponseEntity updateCompanyInformation(@RequestBody CompanyInformation companyInformation){
        companyInformationService.updateCompanyInformation(companyInformation);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
