package com.qelaj.trockenbau.app.controller;

import com.qelaj.trockenbau.app.entity.ContactRequest;
import com.qelaj.trockenbau.app.service.ContactRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/contact-request")
public class ContactRequestController {
    private final ContactRequestService contactRequestService;

    @Autowired
    public ContactRequestController(ContactRequestService contactRequestService) {
        this.contactRequestService = contactRequestService;
    }

    @GetMapping("/id/{id}")
    private ResponseEntity getClientById(@PathVariable Long id){
        Optional<ContactRequest> contactRequest = contactRequestService.getContactRequest(id);
        if(contactRequest.isPresent()){
            return ResponseEntity.ok(contactRequest);
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }

    @GetMapping("/all")
    private ResponseEntity getAllContactRequests(){
        return ResponseEntity.ok(contactRequestService.getAllContactRequest());
    }

    @PostMapping("/create")
    private ResponseEntity createContactRequest(@RequestBody ContactRequest contactRequest){
        contactRequestService.createContactRequest(contactRequest);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/update/id/{id}")
    private ResponseEntity updateContactRequest(@PathVariable Long id,@RequestBody ContactRequest contactRequest){
        contactRequestService.updateContactRequest(id,contactRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    private ResponseEntity deleteContactRequestById(@PathVariable Long id){
        contactRequestService.deleteContactRequest(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/search")
    private ResponseEntity getAllClients(@RequestParam String value,@RequestParam int pageNumber, @RequestParam int dataSize){
        Pageable pagination = PageRequest.of(pageNumber, dataSize);
        if(value.equals("")){
            return ResponseEntity.ok(contactRequestService.getAllWithoutSearch(pagination));
        }
        return ResponseEntity.ok(contactRequestService.searchContactRequests(value,pagination));
    }
}
