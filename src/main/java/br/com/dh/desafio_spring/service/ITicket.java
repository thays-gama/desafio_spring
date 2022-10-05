package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.model.Article;
import br.com.dh.desafio_spring.model.ArticleTicket;
import br.com.dh.desafio_spring.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface ITicket {
    Optional<Ticket> saveTicket(List<ArticleTicket> articles);
    List<Ticket> getAll();
}
