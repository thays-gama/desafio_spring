package br.com.dh.desafio_spring.controller;

import br.com.dh.desafio_spring.exception.EmailAlreadyRegisteredException;
import br.com.dh.desafio_spring.exception.RequiredFieldException;
import br.com.dh.desafio_spring.model.Client;
import br.com.dh.desafio_spring.service.IClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final IClient clientService;

    public ClientController(IClient clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client client) throws RequiredFieldException, EmailAlreadyRegisteredException {
        return clientService.save(client);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Client>> getAllClients(){
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping(params = "state")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Client>> getClientsByState(@RequestParam String state){
        return new ResponseEntity<>(clientService.getByState(state), HttpStatus.OK);
    }
}
