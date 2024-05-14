package com.qelaj.trockenbau.app.service;

import com.qelaj.trockenbau.app.entity.Client;
import com.qelaj.trockenbau.app.entity.Invoice;
import com.qelaj.trockenbau.app.entity.Project;
import com.qelaj.trockenbau.app.repository.ClientRepository;
import com.qelaj.trockenbau.app.repository.InvoiceRepository;
import com.qelaj.trockenbau.app.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private final ProjectRepository projectRepository;


    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, ClientRepository clientRepository, ProjectRepository projectRepository) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
        this.projectRepository = projectRepository;
    }

    public void createInvoice(Invoice invoice) {
        Optional<Project> project = projectRepository.findById(invoice.getProjectId());
        if(project.isPresent()){
            invoice.setProject(project.get());
            invoiceRepository.save(invoice);
        }
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    public Optional<Invoice> getInvoice(Long id) {
        return invoiceRepository.findById(id);
    }

    public int updateInvoice(Long id,Invoice invoice) {
        Optional<Invoice> invoiceFromDB = invoiceRepository.findById(id);
        if(invoiceFromDB.isEmpty()) {
            return 404;
        }
        invoice.setId(id);
        invoice.setProject(invoiceFromDB.get().getProject());
        invoiceRepository.save(invoice);
        return 200;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public List<Invoice> getAllInvoicesByProjectId(Long projectId) {
        return invoiceRepository.findByProjectId(projectId);
    }

    public Page<Invoice> searchInvoices(String value, Long projectId, Pageable pageable){
        value = value.toLowerCase();
        return invoiceRepository.findInvoicesByValueAndProjectId(value,projectId,pageable);
    }

    public Page<Invoice> getAllWithoutSearch(Long projectId,Pageable pageable){
        return invoiceRepository.findByProjectId(projectId,pageable);
    }
}
