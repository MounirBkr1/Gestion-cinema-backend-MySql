package com.mnr.gestioncinema.dao;

import com.mnr.gestioncinema.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;

@RepositoryRestResource
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
