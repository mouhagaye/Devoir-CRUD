package com.ept.dic2git.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="COMPTES")

public class Compte {
    @Id
    @GeneratedValue
    private int id;
    private double solde;
    private double decouvert;

    @ManyToOne
    @JoinColumn(name = "agence_code")
    private Agence agence;

    @ManyToOne
    @JoinColumn(name = "cliend_id")
    private Client client;
}
