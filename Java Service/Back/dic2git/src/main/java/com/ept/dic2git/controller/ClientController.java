package com.ept.dic2git.controller;

import com.ept.dic2git.entity.Client;
import com.ept.dic2git.service.ClientService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientService service;
    @PostMapping("/addClient")
    public Client addClient(@RequestBody Client client){
        return service.saveClient(client);
    }
    @PostMapping("/addClients")
    public List<Client> addClients(@RequestBody List<Client> clients){
        return service.saveClients(clients);
    }
    @GetMapping("/clients")
    public List<Client> findAllClients(){
        return service.getClients();
    }
    @GetMapping("/clients/{code}")
    public Client findAllClientsByCode(@PathVariable int id){
        return service.getClientById(id);
    }
    @PutMapping("/updateClient")
    public Client updateClient(@PathVariable Client client){
        return service.updateClient(client);
    }
    @DeleteMapping("deleteClient/{code}")
    public String deleteClient(@PathVariable int id){
        return service.deleteClient(id);
    }
}