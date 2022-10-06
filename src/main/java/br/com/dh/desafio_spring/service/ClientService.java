package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.exception.EmailAlreadyRegisteredException;
import br.com.dh.desafio_spring.exception.RequiredFieldException;
import br.com.dh.desafio_spring.model.Client;
import br.com.dh.desafio_spring.repository.ClientRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClient
{
    private final ClientRepo repo;
    private Integer counter;

    public ClientService(ClientRepo repo) {
        this.repo = repo;
        this.counter = repo.getClients().size();
    }

    @Override
    public Client save(Client client) throws EmailAlreadyRegisteredException, RequiredFieldException {
        if(getExistingClient(client).isPresent()){
            throw new EmailAlreadyRegisteredException("Esse email já é registrado.");
        }

        if(!validateFields(client).isEmpty()){
            String fields = validateFields(client).stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(", "));
            throw new RequiredFieldException("O(s) campo(s) "+fields+" é/são obrigatório(s).");
        }
        client.setClientId(++counter);
        return repo.saveClient(client);
    }

    private Optional<Client> getExistingClient(Client client){
        return repo.getClients().stream()
                .filter(c->c.getEmail().equalsIgnoreCase(client.getEmail()))
                .findFirst();
    }

    private List<String> validateFields(Client client){
        List list = new ArrayList<>();
        if(client.getName() == null){
            list.add("name");
        }
        if(client.getLastName() == null){
            list.add("lastName");
        }
        if(client.getEmail() == null){
            list.add("email");
        }
        if(client.getState() == null){
            list.add("state");
        }
        return list;
    }

    public List<Client> getAll(){
        return repo.getAll();
    }
    public List<Client> getByState(String state){
        return this.getAll().stream()
                .filter(item-> item.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }
}
