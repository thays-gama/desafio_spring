package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.model.Article;
import br.com.dh.desafio_spring.model.ArticleTicket;
import br.com.dh.desafio_spring.model.Ticket;
import br.com.dh.desafio_spring.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TicketService implements ITicket{
    @Autowired
    private TicketRepo repo;

    @Override
    public Optional<Ticket> saveTicket(List<ArticleTicket> articles) {
        return repo.saveTicket(articles);
    }

    @Override
    public List<Ticket> getAll() {
        return repo.getAll();
    }
}
