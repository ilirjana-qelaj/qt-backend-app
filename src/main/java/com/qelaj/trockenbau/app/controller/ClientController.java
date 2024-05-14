package com.qelaj.trockenbau.app.controller;

import com.qelaj.trockenbau.app.entity.Client;
import com.qelaj.trockenbau.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/id/{id}")
    private ResponseEntity getClientById(@PathVariable Long id){
        Optional<Client> client = clientService.getClient(id);
        if(client.isPresent()){
            return ResponseEntity.ok(client);
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }

    @GetMapping("/all")
    private ResponseEntity getAllClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PostMapping("/create")
    private ResponseEntity createClient(@RequestBody Client client){
        clientService.createClient(client);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/update/id/{id}")
    private ResponseEntity updateClient(@PathVariable Long id,@RequestBody Client client){
        clientService.updateClient(id,client);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    private ResponseEntity deleteClientById(@PathVariable Long id){
       clientService.deleteClient(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/search")
    private ResponseEntity getAllClients(@RequestParam String value,@RequestParam int pageNumber, @RequestParam int dataSize){
        Pageable pagination = PageRequest.of(pageNumber, dataSize);
        if(value.equals("")){
            return ResponseEntity.ok(clientService.getAllWithoutSearch(pagination));
        }
        return ResponseEntity.ok(clientService.searchClients(value,pagination));
    }

}
