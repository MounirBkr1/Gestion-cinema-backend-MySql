package com.mnr.gestioncinema.dao;

import com.mnr.gestioncinema.entities.Cinema;
import com.mnr.gestioncinema.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;

@RepositoryRestResource
public interface SalleRepository extends JpaRepository<Salle, BigDecimal> {
}
