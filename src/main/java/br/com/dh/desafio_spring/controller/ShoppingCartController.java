package br.com.dh.desafio_spring.controller;

import br.com.dh.desafio_spring.model.ShoppingCart;
import br.com.dh.desafio_spring.service.IShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shoppingCarts")
public class ShoppingCartController {

    @Autowired
    private IShoppingCart shoppingCartService;

    @PostMapping
    public ResponseEntity<ShoppingCart> saveShoppingCart(@RequestParam Integer[] ticketId){
        return new ResponseEntity<>(shoppingCartService.save(ticketId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCart>> findAllShoppingCart(){
        return new ResponseEntity<>(shoppingCartService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> findAllShoppingCart(@PathVariable Integer id){
        shoppingCartService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
