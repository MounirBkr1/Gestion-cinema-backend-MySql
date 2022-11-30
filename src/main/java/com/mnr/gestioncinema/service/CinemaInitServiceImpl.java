package com.mnr.gestioncinema.service;

import com.mnr.gestioncinema.entities.*;
import com.mnr.gestioncinema.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService{

    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public void initVilles() {
        Stream.of("casa","kech","rabat","tanger").forEach(v -> {
            Ville ville= new Ville();
            ville.setName(v);
            villeRepository.save(ville);
        });

    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v ->{
            Stream.of("Megarama","Imax","Fonoun","Chahrazad","daouk=liz").forEach(c ->{
                Cinema cinema=new Cinema();
                cinema.setName(c);
                cinema.setNombreSalles(3 +(int) (Math.random()*7)); //compris entre 3 et 10
                cinema.setVille(v);
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(c ->{
            for(int i=0; i<c.getNombreSalles(); i++){
                Salle salle=new Salle();
                salle.setName("Salle"+(i+1));
                salle.setCinema(c);
                salle.setNombrePlace(15 +(int)(Math.random()*20));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(s->{
            for(int i=0; i<s.getNombrePlace();i++){
                Place place = new Place();
                place.setNumero(i+1);
                place.setSalle(s);
                placeRepository.save(place);

            }
        });

    }

    @Override
    public void initSeances() {
        DateFormat df = new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s->{
            Seance seance=new Seance();
            try {
                seance.setHeureDebut(df.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        });
    }

    @Override
    public void initCategories() {
        Stream.of("Histoire","action","fiction","drama").forEach(cat->{
            Categorie categorie=new Categorie();
            categorie.setName(cat);
            categorieRepository.save(categorie);

        });
    }

    @Override
    public void initFilmes() {
        double[] duree=new double[]{1,1.5,2,2.5,3};
        List<Categorie> categories=categorieRepository.findAll();
        Stream.of("Game of truth", "cartoon","remance","spolitgh","trapped").forEach(titreFilm->{
            Film film= new Film();
            film.setTitre(titreFilm);
            film.setDuree(duree[new Random().nextInt(duree.length)]);
            film.setPhoto(titreFilm.replaceAll(" ",""));
            film.setCategorie(categories.get(new Random().nextInt(categories.size())));
            filmRepository.save(film);
        });

    }

    @Override
    public void initProjections() {
        double [] prices= new double[]{30,50,60,70,90,100};
        villeRepository.findAll().forEach(ville->{
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle->{
                    filmRepository.findAll().forEach(film->{
                        seanceRepository.findAll().forEach(seance->{
                            Projection projection = new Projection();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setPrix(prices[new Random().nextInt(prices.length)]);
                            projection.setSalle(salle);
                            projection.setSeance(seance);
                            projectionRepository.save(projection);
                        });


                    });
                });

            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(p->{
            p.getSalle().getPlaces().forEach(place->{
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(p.getPrix());
                ticket.setProjection(p);
                ticket.setReservee(false);
                ticketRepository.save(ticket);
            });
        });
    }
}