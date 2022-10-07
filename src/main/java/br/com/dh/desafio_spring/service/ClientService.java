package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.dto.ClientDTO;
import br.com.dh.desafio_spring.exception.EmailAlreadyRegisteredException;
import br.com.dh.desafio_spring.exception.NotFoundException;
import br.com.dh.desafio_spring.exception.RequiredFieldException;
import br.com.dh.desafio_spring.model.Client;
import br.com.dh.desafio_spring.repository.ClientRepo;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ClientService implements IClient
{
    private final ClientRepo repo;
    private Integer counter;

    public ClientService(ClientRepo repo) {
        this.repo = repo;
        this.counter = repo.getAll().size();
    }

    @Override
    public ClientDTO save(Client client) {
        if(getExistingClient(client).isPresent()){
            throw new EmailAlreadyRegisteredException("Esse email já é registrado.");
        }

        String fields = validateFields(client);
        if(fields!=""){
            throw new RequiredFieldException("O(s) campo(s) "+fields+" é/são obrigatório(s).");
        }
        client.setClientId(++counter);
        return new ClientDTO(repo.saveClient(client));
    }

    @Override
    public ClientDTO findById(Integer id){
        Optional<Client> client = repo.findById(id);
        if(client.isEmpty()){
            throw new NotFoundException("Cliente não encontrado.");
        }
        log.printf(Level.INFO, "Cliente com id "+id+" encontrado.");
        return new ClientDTO(client.get());
    }

    @Override
    public void removeById(Integer id) {
        Optional<Client> client = repo.findById(id);
        if(client.isEmpty()){
            throw new NotFoundException("Cliente não encontrado.");
        }
        repo.removeById(id);
    }

    private Optional<Client> getExistingClient(Client client){
        return repo.getAll().stream()
                .filter(c->c.getEmail().equalsIgnoreCase(client.getEmail()))
                .findFirst();
    }

    private String validateFields(Client client){
        String nullFields = "";
        if(client.getName() == null){
            nullFields+="name";
        }
        if(client.getLastName() == null){
            nullFields+="lastName";
        }
        if(client.getEmail() == null){
            nullFields+="email";
        }
        if(client.getState() == null){
            nullFields+="state";
        }
        return nullFields;
    }
    @Override
    public List<Client> getAll(){
        List<Client> clients = repo.getAll();

        if (clients.isEmpty()) {
            throw new NotFoundException("Objeto não encontrado");
        }

        return clients;
    }

    @Override
    public List<Client> getByState(String state){
        List<Client> clients = this.getAll().stream()
                .filter(item-> item.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());

        if (clients.isEmpty()) {
            throw new NotFoundException("Cliente(s) não encontrado(s)");
        }

        return clients;
    }
}
