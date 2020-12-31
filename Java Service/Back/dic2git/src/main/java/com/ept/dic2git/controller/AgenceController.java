package com.ept.dic2git.controller;

import com.ept.dic2git.entity.Agence;
import com.ept.dic2git.service.AgenceService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgenceController {
    @Autowired
    private AgenceService service;

    @PostMapping("/addAgence")
    public Agence addAgence(@RequestBody Agence agence){
        return service.saveAgence(agence);
    }
    @PostMapping("/addAgences")
    public List<Agence> addAgences(@RequestBody List<Agence> agences){
        return service.saveAgences(agences);
    }
    @GetMapping("/agences")
    public List<Agence> findAllAgences(){
        return service.getAgences();
    }
    @GetMapping("/agences/{code}")
    public Agence findAllAgencesByCode(@PathVariable String code){
        return service.getAgenceByCode(code);
    }
    @PutMapping("/updateAgence")
    public Agence updateAgence(@RequestBody Agence agence){
        return service.updateAgence(agence);
    }
    @DeleteMapping("deleteAgence/{code}")
    public String deleteAgence(@PathVariable String code){
        return service.deleteAgence(code);
    }
}
