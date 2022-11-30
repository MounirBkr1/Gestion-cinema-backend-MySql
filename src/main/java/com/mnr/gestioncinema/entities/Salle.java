package com.mnr.gestioncinema.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int nombrePlace;

    @ManyToOne
    private Cinema cinema;

    @OneToMany(mappedBy="salle")
    private Collection<Place> places;

    @OneToMany(mappedBy="salle")
    private Collection<Projection> projections;

}