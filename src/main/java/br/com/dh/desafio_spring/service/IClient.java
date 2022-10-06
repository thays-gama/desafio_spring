package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.dto.ClientDTO;
import br.com.dh.desafio_spring.exception.EmailAlreadyRegisteredException;
import br.com.dh.desafio_spring.exception.RequiredFieldException;
import br.com.dh.desafio_spring.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClient {
    ClientDTO save(Client client) throws EmailAlreadyRegisteredException, RequiredFieldException;
    List<Client> getAll();
    List<Client> getByState(String state);
    ClientDTO findById(Integer id);
    void removeById(Integer id);
}
