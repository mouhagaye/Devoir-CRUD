package com.ept.dic2git.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTS")

public class Client {
    @Id
    @GeneratedValue
    private int id;
    private String prenom;
    private String nom;
    @Basic
    private java.sql.Date date_naissance;

    @OneToMany(mappedBy = "client")
    private List<Compte> comptes;
}
