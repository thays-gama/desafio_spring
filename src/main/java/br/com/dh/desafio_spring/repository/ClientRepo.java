package br.com.dh.desafio_spring.repository;

import br.com.dh.desafio_spring.exception.EmailAlreadyRegisteredException;
import br.com.dh.desafio_spring.exception.RequiredFieldException;
import br.com.dh.desafio_spring.model.Client;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Log4j2
public class ClientRepo {

    private List<Client> clients;
    private final String linkFile = "src/main/resources/clients.json";
    private ObjectMapper mapper = new ObjectMapper();

    public ClientRepo() {
        this.clients = new ArrayList<>(getAll());
    }

    public Client saveClient(Client client) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        clients.add(client);

        try {
            writer.writeValue(new File(linkFile), clients);
        } catch (Exception ex) {
            log.printf(Level.WARN, "Erro ao salvar os dados!");
        }
        log.printf(Level.INFO, "Novo cliente salvo.");
        return client;
    }

    public Optional<Client> findById(Integer id){
        return clients.stream()
                .filter(client -> client.getClientId() == id)
                .findFirst();
    }

    public void removeById(Integer id) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        clients = clients.stream().filter(client -> client.getClientId()!=id)
                .collect(Collectors.toList());
        try {
            writer.writeValue(new File(linkFile), clients);
        } catch (Exception ex) {
            log.printf(Level.WARN, "Erro ao salvar os dados!");
        }
        log.printf(Level.INFO, "Cliente com id "+id+" deletado.");
    }

    public List<Client> getClients() {
        return clients;
    }

    private List<Client> getAll(){
        try{
            log.printf(Level.INFO, "Arquivo "+linkFile+" carregado");
            return Arrays.asList(mapper.readValue(new File(linkFile), Client[].class));
        } catch (Exception ex){
            log.printf(Level.WARN, "Erro ao ler o arquivo. O arquivo "+linkFile+" não foi carregado");
        }
        return Collections.emptyList();
    }
}
