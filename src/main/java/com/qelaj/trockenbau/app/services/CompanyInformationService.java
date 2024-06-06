package com.qelaj.trockenbau.app.services;

import com.qelaj.trockenbau.app.entities.CompanyInformation;
import com.qelaj.trockenbau.app.repositories.CompanyInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyInformationService {

    private final CompanyInformationRepository companyInformationRepository;

    public CompanyInformationService(CompanyInformationRepository companyInformationRepository) {
        this.companyInformationRepository = companyInformationRepository;
    }

    public void createCompanyInformation(CompanyInformation companyInformation) {
        companyInformationRepository.save(companyInformation);
    }

    public Optional<CompanyInformation> getCompanyInformation(Long id) {
        return companyInformationRepository.findById(id);
    }

    public int updateCompanyInformation(CompanyInformation companyInformation) {
        List<CompanyInformation> companyInformationList = companyInformationRepository.findAll();
        if (companyInformationList.size()>0){
            CompanyInformation companyInformationFromDb = companyInformationList.get(0);
            companyInformation.setId(companyInformationFromDb.getId());
            companyInformationRepository.save(companyInformation);
        }else{
            createCompanyInformation(companyInformation);
        }
        return 200;
    }

    public List<CompanyInformation> getAllCompanyInformation() {
        return companyInformationRepository.findAll();
    }

    public List<CompanyInformation> getCompanyInformationForContact() {
        return companyInformationRepository.getCompanyInformation();
    }
}
