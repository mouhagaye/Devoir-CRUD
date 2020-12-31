package com.ept.dic2git.service;

import com.ept.dic2git.entity.Agence;
import com.ept.dic2git.repository.AgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AgenceService {
    @Autowired
    private AgenceRepository repository;

    public Agence saveAgence(Agence agence){
        return repository.save(agence);
    }
    public List<Agence> saveAgences(List<Agence> agences){
        return repository.saveAll(agences);
    }
    public List<Agence> getAgences(){
        return repository.findAll();
    }
    public Agence getAgenceByCode(String code){
        return repository.findById(code).orElse(null);
    }
    public String deleteAgence(String code){
        repository.deleteById(code);
        return "Supprime avec succes";
    }
    public Agence updateAgence(Agence agence){
        Agence existingAgence = repository.findById(agence.getCode()).orElse(null);
        existingAgence.setCode(agence.getCode());
        existingAgence.setNom(agence.getNom());
        existingAgence.setAdresse(agence.getAdresse());
        existingAgence.setTelephone(agence.getTelephone());
        return repository.save(existingAgence);

    }
}
