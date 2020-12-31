package com.ept.dic2git.service;

import com.ept.dic2git.entity.Client;
import com.ept.dic2git.entity.Compte;
import com.ept.dic2git.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Client saveClient(Client client){
        return repository.save(client);
    }
    public List<Client> saveClients(List<Client> clients){
        return repository.saveAll(clients);
    }
    public List<Client> getClients(){
        return repository.findAll();
    }
    public Client getClientById(int id){
        return repository.findById(id).orElse(null);
    }
    public String deleteClient(int id){
        repository.deleteById(id);
        return "Supprime avec succes";
    }
    public Client updateClient(Client client){
        Client existingClient = repository.findById(client.getId()).orElse(null);
        existingClient.setId(client.getId());
        existingClient.setNom(client.getNom());
        existingClient.setPrenom(client.getPrenom());
        existingClient.setDate_naissance(client.getDate_naissance());
        return repository.save(existingClient);

    }
}