package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.exception.EmailAlreadyRegisteredException;
import br.com.dh.desafio_spring.exception.RequiredFieldException;
import br.com.dh.desafio_spring.model.Client;

public interface IClient {
    Client save(Client client) throws EmailAlreadyRegisteredException, RequiredFieldException;
}
