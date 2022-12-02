package com.mnr.gestioncinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private String realisateur;
    private Date dateSortie;
    private double duree;
    private String photo;

    @OneToMany(mappedBy="film")
    //eviter les boucles infinies ds les associations biderectionnelle
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projections;

    @ManyToOne
    private Categorie categorie;

}