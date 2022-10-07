package br.com.dh.desafio_spring.controller;

import br.com.dh.desafio_spring.model.ShoppingCart;
import br.com.dh.desafio_spring.service.IShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe que representa o controller do Carrinho de Compras
 * @author thays-gama
 */
@RestController
@RequestMapping("/api/v1/shoppingCarts")
public class ShoppingCartController {

    @Autowired
    private IShoppingCart shoppingCartService;

    /**
     * @param ticketId ticket Ids a serem associados ao carrinho de compras
     * @return carrinho de compras salvo
     * @since 1.0
     */
    @PostMapping
    public ResponseEntity<ShoppingCart> saveShoppingCart(@RequestParam Integer[] ticketId){
        return new ResponseEntity<>(shoppingCartService.save(ticketId), HttpStatus.CREATED);
    }

    /**
     * @return lista com todos os carrinhos de compras salvos
     * @since 1.0
     */
    @GetMapping
    public ResponseEntity<List<ShoppingCart>> findAllShoppingCart(){
        return new ResponseEntity<>(shoppingCartService.findAll(), HttpStatus.OK);
    }

    /**
     * @param id id do carrinho a ser deletado
     * @since 1.0
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> findAllShoppingCart(@PathVariable Integer id){
        shoppingCartService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
