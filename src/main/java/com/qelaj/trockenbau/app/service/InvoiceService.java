package com.qelaj.trockenbau.app.service;

import com.qelaj.trockenbau.app.entity.Client;
import com.qelaj.trockenbau.app.entity.Invoice;
import com.qelaj.trockenbau.app.entity.Project;
import com.qelaj.trockenbau.app.repository.ClientRepository;
import com.qelaj.trockenbau.app.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, ClientRepository clientRepository) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
    }

    public void createInvoice(Invoice invoice) {
        Optional<Client> client = clientRepository.findById(invoice.getClientId());
        if(client.isPresent()){
//            invoice.setProject();
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
        Optional<Invoice> clientFromDb = invoiceRepository.findById(id);
        if(clientFromDb.isEmpty()) {
            return 404;
        }
        invoice.setId(id);
        invoiceRepository.save(invoice);
        return 200;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public List<Invoice> getAllInvoicesByProjectId(Long projectId) {
        return invoiceRepository.findByProjectId(projectId);
    }
}
