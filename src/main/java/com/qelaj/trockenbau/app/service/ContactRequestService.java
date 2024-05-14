package com.qelaj.trockenbau.app.service;

import com.qelaj.trockenbau.app.entity.Client;
import com.qelaj.trockenbau.app.entity.ContactRequest;
import com.qelaj.trockenbau.app.entity.Invoice;
import com.qelaj.trockenbau.app.repository.ContactRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactRequestService {

    private final ContactRequestRepository contactRequestRepository;

    @Autowired
    public ContactRequestService(ContactRequestRepository contactRequestRepository) {
        this.contactRequestRepository = contactRequestRepository;
    }

    public void createContactRequest(ContactRequest contactRequest) {
        contactRequest.setOpened(false);
        contactRequestRepository.save(contactRequest);
    }

    public void deleteContactRequest(Long id) {
        contactRequestRepository.deleteById(id);
    }

    public Optional<ContactRequest> getContactRequest(Long id) {
        Optional<ContactRequest> contactRequest = contactRequestRepository.findById(id);
        if(contactRequest.isPresent()) {
            contactRequest.get().setOpened(true);
            contactRequestRepository.save(contactRequest.get());
        }
        return contactRequest;
    }

    public int updateContactRequest(Long id,ContactRequest contactRequest) {
        Optional<ContactRequest> contactRequestFromDb = contactRequestRepository.findById(id);
        if(contactRequestFromDb.isEmpty()) {
            return 404;
        }
        contactRequest.setId(id);
        contactRequestRepository.save(contactRequest);
        return 200;
    }

    public List<ContactRequest> getAllContactRequest() {
        return contactRequestRepository.findAll();
    }

    public Page<ContactRequest> searchContactRequests(String value, Pageable pageable){
        value = value.toLowerCase();
        return contactRequestRepository.findContactRequestByValueAndProjectId(value,pageable);
    }

    public Page<ContactRequest> getAllWithoutSearch(Pageable pageable){
        return contactRequestRepository.findAll(pageable);
    }

}
