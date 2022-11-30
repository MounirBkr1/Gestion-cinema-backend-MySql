package com.mnr.gestioncinema.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seance implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //timstamp on ne garde que h min et s , pas de date
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureDebut;

    //projection uniderectionnelle , pas besoin de specifier OneToMany

}