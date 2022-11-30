package com.mnr.gestioncinema.dao;

import com.mnr.gestioncinema.entities.Categorie;
import com.mnr.gestioncinema.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;

//add api rest
@RepositoryRestResource
public interface CategorieRepository extends JpaRepository<Categorie, BigDecimal> {
}
