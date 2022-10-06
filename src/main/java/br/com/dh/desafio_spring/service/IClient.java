package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.exception.EmailAlreadyRegisteredException;
import br.com.dh.desafio_spring.exception.RequiredFieldException;
import br.com.dh.desafio_spring.model.Client;

import java.util.List;

public interface IClient {
    Client save(Client client) throws EmailAlreadyRegisteredException, RequiredFieldException;
    List<Client> getAll();
    List<Client> getByState(String state);
}
