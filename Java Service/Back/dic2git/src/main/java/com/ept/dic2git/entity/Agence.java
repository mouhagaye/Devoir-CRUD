package com.ept.dic2git.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="AGENCES")

public class Agence {
    @Id
    private String code;
    private String adresse;
    private String nom;
    private String telephone;

    @OneToMany(mappedBy = "agence")
    private List<Compte> comptes;

}
