package com.qelaj.trockenbau.app.controller;

import com.qelaj.trockenbau.app.entity.Client;
import com.qelaj.trockenbau.app.entity.Invoice;
import com.qelaj.trockenbau.app.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/id/{id}")
    private ResponseEntity getInvoiceById(@PathVariable Long id){
        Optional<Invoice> client = invoiceService.getInvoice(id);
        if(client.isPresent()){
            return ResponseEntity.ok(client);
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }

    @GetMapping("/all")
    private ResponseEntity getAllInvoices(){
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @PostMapping("/create")
    private ResponseEntity createInvoice(@RequestBody Invoice invoice){
        invoiceService.createInvoice(invoice);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/update/id/{id}")
    private ResponseEntity updateInvoice(@PathVariable Long id,@RequestBody Invoice invoice){
        invoiceService.updateInvoice(id, invoice);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    private ResponseEntity deleteInvoiceById(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    private ResponseEntity getAllInvoicesByProjectId(@PathVariable Long projectId){
        return ResponseEntity.ok(invoiceService.getAllInvoicesByProjectId(projectId));
    }

    @GetMapping("/search")
    private ResponseEntity getAllInvoicesBySearch(@RequestParam String value,@RequestParam Long projectId,@RequestParam int pageNumber, @RequestParam int dataSize){
        Pageable pagination = PageRequest.of(pageNumber, dataSize);
        if(value.equals("")){
            return ResponseEntity.ok(invoiceService.getAllWithoutSearch(projectId,pagination));
        }
        return ResponseEntity.ok(invoiceService.searchInvoices(value,projectId,pagination));
    }
}
