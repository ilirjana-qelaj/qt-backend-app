package com.qelaj.trockenbau.app.controllers;

import com.qelaj.trockenbau.app.entities.Invoice;
import com.qelaj.trockenbau.app.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private ResponseEntity getAllInvoicesBySearch(@RequestParam String value,@RequestParam Long projectId,@RequestParam int pageNumber,
                                                  @RequestParam int dataSize, @RequestParam(required = false) String direction,@RequestParam(required = false) String sortBy){
        Pageable pagination=null;
        if(direction != null && sortBy != null){
            Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            pagination = PageRequest.of(pageNumber, dataSize,sort);
        }else{
            pagination = PageRequest.of(pageNumber, dataSize);
        }
        if(value.equals("")){
            return ResponseEntity.ok(invoiceService.getAllWithoutSearch(projectId,pagination));
        }
        return ResponseEntity.ok(invoiceService.searchInvoices(value,projectId,pagination));
    }
}
