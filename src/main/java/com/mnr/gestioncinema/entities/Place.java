package com.mnr.gestioncinema.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private double longitude,latitutde,altitude;

    @ManyToOne
    private Salle salle;

    @OneToMany(mappedBy="place")
    private Collection<Ticket> tickets;

}