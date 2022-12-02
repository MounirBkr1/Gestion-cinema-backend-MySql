package com.mnr.gestioncinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Ticket> tickets;

}