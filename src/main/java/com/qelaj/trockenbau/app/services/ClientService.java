package com.qelaj.trockenbau.app.services;

import com.qelaj.trockenbau.app.entities.Client;
import com.qelaj.trockenbau.app.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Optional<Client> getClient(Long id) {
        return clientRepository.findById(id);
    }

    public int updateClient(Long id,Client client) {
        Optional<Client> clientFromDb = clientRepository.findById(id);
        if(clientFromDb.isEmpty()) {
            return 404;
        }
        client.setId(id);
        clientRepository.save(client);
        return 200;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Page<Client> searchClients(String value, Pageable pageable){
        value = value.toLowerCase();
        return clientRepository.findClientsByValue(value,pageable);
    }

    public Page<Client> getAllWithoutSearch(Pageable pageable){
        return clientRepository.findAll(pageable);
    }
}
