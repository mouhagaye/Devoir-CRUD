package com.ept.dic2git.controller;

import com.ept.dic2git.entity.Compte;
import com.ept.dic2git.service.CompteService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompteController {
    @Autowired
    private CompteService service;

    @PostMapping("/addCompte")
    public Compte addCompte(@RequestBody Compte compte){
        return service.saveCompte(compte);
    }
    @PostMapping("/addComptes")
    public List<Compte> addComptes(@RequestBody List<Compte> comptes){
        return service.saveComptes(comptes);
    }
    @GetMapping("/comptes")
    public List<Compte> findAllComptes(){
        return service.getComptes();
    }
    @GetMapping("/comptes/{code}")
    public Compte findAllComptesByCode(@PathVariable int id){
        return service.getCompteById(id);
    }
    @PutMapping("/updateCompte")
    public Compte updateCompte(@PathVariable Compte compte){
        return service.updateCompte(compte);
    }
    @DeleteMapping("deleteCompte/{code}")
    public String deleteCompte(@PathVariable int id){
        return service.deleteCompte(id);
    }
}