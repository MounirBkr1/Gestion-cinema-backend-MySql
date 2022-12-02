package com.mnr.gestioncinema;

import com.mnr.gestioncinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionCinemaApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(GestionCinemaApplication.class, args);
    }


    @Autowired
    private ICinemaInitService cinemaInitService;
    @Override
    public void run(String... args) throws Exception {
        cinemaInitService.initVilles();
        cinemaInitService.initCinemas();
        cinemaInitService.initPlaces();
        cinemaInitService.initSeances();
        cinemaInitService.initSalles();
        cinemaInitService.initCategories();
        cinemaInitService.initCategories();
        cinemaInitService.initFilmes();
        cinemaInitService.initProjections();
        cinemaInitService.initTickets();

    }
}
