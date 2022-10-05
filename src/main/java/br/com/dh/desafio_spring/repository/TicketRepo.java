package br.com.dh.desafio_spring.repository;

import br.com.dh.desafio_spring.model.Article;
import br.com.dh.desafio_spring.model.ArticleTicket;
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

    public Optional<Ticket> saveTicket(List<ArticleTicket> articles){
        ArticleRepo articleRepo = new ArticleRepo();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        Ticket ticket = new Ticket();
        ticket.setId(getAll().size() + 1);

        articles.stream().forEach(article -> {
            Article articleCompleto = articleRepo.getArticleById(article.getProductId());

            articleCompleto.setQuantity(article.getQuantity());

            ticket.addArticle(articleCompleto);
        });

        List<Ticket> tickets = new ArrayList<>(getAll());
        tickets.add(ticket);

        try{
            writer.writeValue(new File(linkFile), tickets);
        } catch (Exception ex){
            System.out.println("Erro ao salvar os dados!");
        }

        return Optional.of(ticket);
    }

    public List<Ticket> getAll(){
        try{
            return Arrays.asList(mapper.readValue(new File(linkFile), Ticket[].class));
        } catch (Exception ex){
            System.out.println("Erro ao ler o arquivo");
        }

        return null;
    }
}
