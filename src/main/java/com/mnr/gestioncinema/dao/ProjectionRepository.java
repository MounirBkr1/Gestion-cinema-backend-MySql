package com.mnr.gestioncinema.dao;

import com.mnr.gestioncinema.entities.Cinema;
import com.mnr.gestioncinema.entities.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;

@RepositoryRestResource
public interface ProjectionRepository extends JpaRepository<Projection, Long> {
}
