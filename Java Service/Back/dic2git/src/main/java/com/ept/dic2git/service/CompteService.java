package com.ept.dic2git.service;

import com.ept.dic2git.entity.Agence;
import com.ept.dic2git.entity.Compte;
import com.ept.dic2git.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompteService {
    @Autowired
    private CompteRepository repository;

    public Compte saveCompte(Compte compte){
        return repository.save(compte);
    }
    public List<Compte> saveComptes(List<Compte> comptes){
        return repository.saveAll(comptes);
    }
    public List<Compte> getComptes(){
        return repository.findAll();
    }
    public Compte getCompteById(int id){
        return repository.findById(id).orElse(null);
    }
    public String deleteCompte(int id){
        repository.deleteById(id);
        return "Supprime avec succes";
    }
    public Compte updateCompte(Compte compte){
        Compte existingCompte = repository.findById(compte.getId()).orElse(null);
        existingCompte.setId(compte.getId());
        existingCompte.setSolde(compte.getSolde());
        existingCompte.setDecouvert(compte.getDecouvert());

        return repository.save(existingCompte);

    }
}