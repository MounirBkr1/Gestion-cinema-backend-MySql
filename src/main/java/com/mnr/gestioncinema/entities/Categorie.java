package com.mnr.gestioncinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75)
    private String name;

    @OneToMany(mappedBy="categorie")
    //pas la peine de me donner les films de categorie=eviter les boucles infinies
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Film> films;

}