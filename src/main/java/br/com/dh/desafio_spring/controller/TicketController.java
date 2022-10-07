package br.com.dh.desafio_spring.controller;

import br.com.dh.desafio_spring.model.Article;
import br.com.dh.desafio_spring.model.ArticleTicket;
import br.com.dh.desafio_spring.model.Ticket;
import br.com.dh.desafio_spring.service.ITicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-request")
public class TicketController {
    @Autowired
    private ITicket ticketService;

    @PostMapping
    public ResponseEntity<Ticket> saveTicket(@RequestBody List<ArticleTicket> articles, @RequestParam int idClient){
        return new ResponseEntity<>(ticketService.saveTicket(articles, idClient).get(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAll(){
        return new ResponseEntity<>(ticketService.getAll(), HttpStatus.OK);
    }
}
