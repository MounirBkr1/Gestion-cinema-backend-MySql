package com.mnr.gestioncinema.web;


import com.mnr.gestioncinema.dao.FilmRepository;
import com.mnr.gestioncinema.dao.TicketRepository;
import com.mnr.gestioncinema.entities.Film;
import com.mnr.gestioncinema.entities.Ticket;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRestController {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private TicketRepository ticketRepository;

    //image est un tableau de bytes,methode auquelle vs donner 'id' du film il vous donne la photo
    @GetMapping(path="/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable (name="id") Long id) throws  Exception{
        Film film;
        film = filmRepository.findById(id).get();
        String photoName= film.getPhoto();

        //System.getProperty("user.home")=path to user folder
        File file= new File(System.getProperty("user.home")+"/gestion-cinema/images/"+photoName);

        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }


    @PostMapping("/payerTickets")
    @Transactional
    //@RequestBody: les donnees sont envoyées au corps de la requete sous forma Json
    public List<Ticket> payerTicket(@RequestBody TicketForm ticketForm){

        List<Ticket> ticketList=new ArrayList<>();

        ticketForm.getTickets().forEach(idTicket->{
            try {
                Ticket ticket= ticketRepository.findById(idTicket).get();
                ticket.setNomClient(ticketForm.getNomClient());
                ticket.setReservee(true);

                ticketRepository.save(ticket);

                ticketList.add(ticket);

            }catch (Exception e) {
                throw new RuntimeException("exeption **************"+e);
            }



        });
        return ticketList;
    }

}


@Getter
@Setter
class TicketForm{
    private String nomClient;
    private int codePayement;
    private List<Long> tickets=new ArrayList<>();
}