package br.com.dh.desafio_spring.repository;

import br.com.dh.desafio_spring.dto.ClientDTO;
import br.com.dh.desafio_spring.exception.OutOfStockException;
import br.com.dh.desafio_spring.exception.NotFoundException;
import br.com.dh.desafio_spring.exception.ServerException;
import br.com.dh.desafio_spring.model.Article;
import br.com.dh.desafio_spring.model.ArticleTicket;
import br.com.dh.desafio_spring.model.Client;
import br.com.dh.desafio_spring.model.Ticket;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepo {
    private final String linkFile = "src/main/resources/tickets.json";
    private ObjectMapper mapper = new ObjectMapper();

    public Optional<Ticket> saveTicket(List<ArticleTicket> articles, int idClient) throws OutOfStockException {
        ArticleRepo articleRepo = new ArticleRepo();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        if(clientExists(idClient).isEmpty()) throw new NotFoundException("Cliente não encontrado");

        Ticket ticket = new Ticket();
        ticket.setId(getAll().size() + 1);
        ticket.setClient(clientExists(idClient).get());

        articles.stream().forEach(article -> {
            Article articleCompleto = articleRepo.getArticleById(article.getProductId());

            if(articleCompleto == null) throw new NotFoundException("Produto não encontrado");
            articleCompleto.applyQuantity(article.getQuantity());

            ticket.addArticle(articleCompleto);
        });

        List<Ticket> tickets = new ArrayList<>(getAll());
        tickets.add(ticket);

        try{
            writer.writeValue(new File(linkFile), tickets);
        } catch (Exception ex){
            throw new ServerException("Ocorreu um erro ao salvar os dados!");
        }

        return Optional.of(ticket);
    }

    public List<Ticket> getAll(){
        try{
            return Arrays.asList(mapper.readValue(new File(linkFile), Ticket[].class));
        } catch (Exception ex){
            throw new ServerException("Ocorreu um erro ao ler os dados!");
        }
    }

    public Optional<Ticket> findById(Integer id){
        return this.getAll().stream()
                .filter(ticket -> ticket.getId() == id)
                .findFirst();
    }

    public Optional<ClientDTO> clientExists(int idClient){
        ClientRepo clientRepo = new ClientRepo();

        if(clientRepo.findById(idClient).isEmpty())
            return Optional.empty();

        return Optional.of(new ClientDTO(clientRepo.findById(idClient).get()));
    }
}
