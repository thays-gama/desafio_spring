package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.model.ShoppingCart;

import java.util.List;

public interface IShoppingCart {
    ShoppingCart save(Integer[] ticketIds);
    List<ShoppingCart> findAll();
    void deleteById(Integer id);
}
